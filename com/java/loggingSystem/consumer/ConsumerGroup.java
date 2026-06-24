package com.java.loggingSystem.consumer;

import com.java.loggingSystem.model.LogEvent;
import com.java.loggingSystem.partition.PartitionStrategy;
import com.java.loggingSystem.processor.LogProcessor;
import com.java.loggingSystem.sink.LogSink;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * One logical subscriber. Owns its own partition queues (independent offsets),
 * a processing chain, and a sink. One worker thread per partition (1:1).
 */
public final class ConsumerGroup {
    private final String groupId;
    private final int partitionCount;
    private final List<BlockingQueue<LogEvent>> partitions = new ArrayList<>();
    private final PartitionStrategy partitioner;
    private final LogProcessor chain;
    private final LogSink sink;
    private final ExecutorService workers;
    private volatile boolean running = true;

    public ConsumerGroup(String groupId, int partitionCount, PartitionStrategy partitioner,
                         LogProcessor chain, LogSink sink) {
        this.groupId = groupId;
        this.partitionCount = partitionCount;
        this.partitioner = partitioner;
        this.chain = chain;
        this.sink = sink;
        for (int i = 0; i < partitionCount; i++)
            partitions.add(new LinkedBlockingQueue<>());
        this.workers = Executors.newFixedThreadPool(partitionCount);
    }

    /** Buffer calls this to deliver this group's copy of an event. */
    public void deliver(LogEvent e) {
        partitions.get(partitioner.selectPartition(e, partitionCount)).offer(e);
    }

    /** One consumer thread per partition — the Kafka sweet spot. */
    public void start() {
        for (int i = 0; i < partitionCount; i++) {
            final int pid = i;
            workers.submit(() -> consumeLoop(pid));
        }
    }

    private void consumeLoop(int pid) {
        BlockingQueue<LogEvent> q = partitions.get(pid);
        while (running || !q.isEmpty()) {
            try {
                LogEvent e = q.poll(100, TimeUnit.MILLISECONDS);
                if (e == null) continue;
                LogEvent out = (chain == null) ? e : chain.process(e);
                if (out != null) sink.write(out);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void shutdown() {
        running = false;
        workers.shutdown();
        try { workers.awaitTermination(2, TimeUnit.SECONDS); }
        catch (InterruptedException ignored) { Thread.currentThread().interrupt(); }
    }
}
