package com.java.loggingSystem.sink;

import com.java.loggingSystem.model.LogEvent;

public interface LogSink {
    void write(LogEvent e);
    String name();
}
