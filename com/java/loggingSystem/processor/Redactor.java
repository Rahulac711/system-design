package com.java.loggingSystem.processor;

import com.java.loggingSystem.model.LogEvent;

/** Masks PII-looking fields. */
public final class Redactor extends LogProcessor {
    @Override
    protected LogEvent handle(LogEvent e) {
        return e.fields.containsKey("email") ? e.withField("email", "***") : e;
    }
}
