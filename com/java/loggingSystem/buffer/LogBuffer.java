package com.java.loggingSystem.buffer;

import com.java.loggingSystem.consumer.ConsumerGroup;
import com.java.loggingSystem.model.LogEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Kafka-like backbone. Fans every event out to all subscribed consumer
 * groups; each group keeps an independent copy (= independent offsets).
 */
public final class LogBuffer {
    private final List<ConsumerGroup> groups = new CopyOnWriteArrayList<>();

    public void subscribe(ConsumerGroup g) {
        groups.add(g);
    }

    public void publish(LogEvent e) {
        for (ConsumerGroup g : groups) g.deliver(e);
    }

    public void startAll() {
        groups.forEach(ConsumerGroup::start);
    }

    public void shutdownAll() {
        groups.forEach(ConsumerGroup::shutdown);
    }
}
