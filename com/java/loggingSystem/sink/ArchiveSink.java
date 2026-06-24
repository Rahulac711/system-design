package com.java.loggingSystem.sink;

import com.java.loggingSystem.model.LogEvent;

public final class ArchiveSink implements LogSink {
    public void write(LogEvent e) { System.out.println("  [S3 raw   ] " + e); }
    public String name() { return "s3-archive"; }
}
