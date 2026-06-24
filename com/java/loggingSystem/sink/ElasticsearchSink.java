package com.java.loggingSystem.sink;

import com.java.loggingSystem.model.LogEvent;

public final class ElasticsearchSink implements LogSink {
    public void write(LogEvent e) { System.out.println("  [ES index ] " + e); }
    public String name() { return "elasticsearch"; }
}
