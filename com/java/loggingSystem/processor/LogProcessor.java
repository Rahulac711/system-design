package com.java.loggingSystem.processor;

import com.java.loggingSystem.model.LogEvent;

/** Each processor handles one concern then delegates. Returning null = drop. */
public abstract class LogProcessor {
    private LogProcessor next;

    public LogProcessor linkTo(LogProcessor n) { this.next = n; return n; }

    public final LogEvent process(LogEvent e) {
        LogEvent out = handle(e);
        if (out == null) return null;
        return (next == null) ? out : next.process(out);
    }

    protected abstract LogEvent handle(LogEvent e);
}
