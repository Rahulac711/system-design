package com.java.loggingSystem.processor;

import com.java.loggingSystem.model.LogEvent;
import com.java.loggingSystem.model.LogLevel;

/** Drops everything below a minimum level (e.g. DEBUG noise in prod). */
public final class LevelFilter extends LogProcessor {
    private final LogLevel min;

    public LevelFilter(LogLevel min) { this.min = min; }

    @Override
    protected LogEvent handle(LogEvent e) {
        return e.level.ordinal() >= min.ordinal() ? e : null;
    }
}
