package com.java.loggingSystem.processor;

import com.java.loggingSystem.model.LogEvent;

/** Adds contextual metadata (here: a datacenter tag). */
public final class Enricher extends LogProcessor {
    private final String dc;

    public Enricher(String dc) { this.dc = dc; }

    @Override
    protected LogEvent handle(LogEvent e) { return e.withField("dc", dc); }
}
