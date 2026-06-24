package com.java.loggingSystem.partition;

import com.java.loggingSystem.model.LogEvent;

public interface PartitionStrategy {
    int selectPartition(LogEvent event, int partitionCount);
}
