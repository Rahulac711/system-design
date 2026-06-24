package com.java.loggingSystem.partition;

import com.java.loggingSystem.model.LogEvent;

/**
 * Hash by service => same service always lands on the same partition (ordering).
 */
public final class ServiceKeyPartitioner implements PartitionStrategy {
    public int selectPartition(LogEvent e, int n) {
        return Math.floorMod(e.service.hashCode(), n);
    }
}
