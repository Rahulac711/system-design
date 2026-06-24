package com.java.loggingSystem;

import com.java.loggingSystem.buffer.LogBuffer;
import com.java.loggingSystem.consumer.ConsumerGroup;
import com.java.loggingSystem.model.LogEvent;
import com.java.loggingSystem.model.LogLevel;
import com.java.loggingSystem.partition.PartitionStrategy;
import com.java.loggingSystem.partition.ServiceKeyPartitioner;
import com.java.loggingSystem.processor.Enricher;
import com.java.loggingSystem.processor.LevelFilter;
import com.java.loggingSystem.processor.LogProcessor;
import com.java.loggingSystem.processor.Redactor;
import com.java.loggingSystem.producer.ServiceLogProducer;
import com.java.loggingSystem.sink.ArchiveSink;
import com.java.loggingSystem.sink.ElasticsearchSink;

/**
 * Entry point for the log aggregation pipeline demo.
 *
 * Flow:     Producer -> Buffer (partitioned, pub-sub) -> ConsumerGroup(s)
 *           each group runs a Chain-of-Responsibility processing pipeline,
 *           then writes to its Sink.
 *
 * Patterns: Builder (LogEvent), Strategy (partitioning),
 *           Chain of Responsibility (processing), Observer / Pub-Sub (fan-out),
 *           Producer-Consumer (partition queues + worker threads).
 */
public class LogAggregationDemo {

    public static void main(String[] args) throws InterruptedException {
        int partitions = 3;
        PartitionStrategy partitioner = new ServiceKeyPartitioner();
        LogBuffer buffer = new LogBuffer();

        // Group 1: indexer — full chain (filter -> enrich -> redact) -> Elasticsearch.
        LogProcessor indexChain = new LevelFilter(LogLevel.INFO);
        indexChain.linkTo(new Enricher("dc-mumbai")).linkTo(new Redactor());
        ConsumerGroup indexer = new ConsumerGroup(
                "indexer", partitions, partitioner, indexChain, new ElasticsearchSink());

        // Group 2: archiver — no chain, raw events straight to S3.
        ConsumerGroup archiver = new ConsumerGroup(
                "archiver", partitions, partitioner, null, new ArchiveSink());

        buffer.subscribe(indexer);    // fan-out target 1
        buffer.subscribe(archiver);   // fan-out target 2
        buffer.startAll();

        ServiceLogProducer app = new ServiceLogProducer(buffer);
        app.emit(LogEvent.builder().service("auth").level(LogLevel.INFO)
                .message("login ok").field("email", "rahul@thg.com").build());
        app.emit(LogEvent.builder().service("auth").level(LogLevel.DEBUG)
                .message("cache hit").build());                 // dropped by indexer, kept by archiver
        app.emit(LogEvent.builder().service("payments").level(LogLevel.ERROR)
                .message("gateway timeout").build());
        app.emit(LogEvent.builder().service("catalog").level(LogLevel.WARN)
                .message("slow query").build());

        Thread.sleep(500);            // let consumers drain
        buffer.shutdownAll();
        System.out.println("done.");
    }
}
