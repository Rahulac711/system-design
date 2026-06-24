package com.java.loggingSystem.producer;

import com.java.loggingSystem.buffer.LogBuffer;
import com.java.loggingSystem.model.LogEvent;

public final class ServiceLogProducer {
    private final LogBuffer buffer;

    public ServiceLogProducer(LogBuffer b) { this.buffer = b; }

    public void emit(LogEvent e) { buffer.publish(e); }
}
