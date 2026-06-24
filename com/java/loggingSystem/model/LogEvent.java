package com.java.loggingSystem.model;

import java.time.Instant;
import java.util.*;

public final class LogEvent {
    public final Instant timestamp;
    public final LogLevel level;
    public final String service;
    public final String message;
    public final Map<String, String> fields;

    private LogEvent(Builder b) {
        this.timestamp = b.timestamp;
        this.level = b.level;
        this.service = b.service;
        this.message = b.message;
        this.fields = Collections.unmodifiableMap(new HashMap<>(b.fields));
    }

    /** Events are immutable, so enrichment returns a new copy. */
    public LogEvent withField(String k, String v) {
        return builder().from(this).field(k, v).build();
    }

    public static Builder builder() { return new Builder(); }

    public static final class Builder {
        private Instant timestamp = Instant.now();
        private LogLevel level = LogLevel.INFO;
        private String service = "unknown";
        private String message = "";
        private Map<String, String> fields = new HashMap<>();

        public Builder from(LogEvent e) {
            this.timestamp = e.timestamp; this.level = e.level;
            this.service = e.service;     this.message = e.message;
            this.fields = new HashMap<>(e.fields);
            return this;
        }
        public Builder timestamp(Instant t) { this.timestamp = t; return this; }
        public Builder level(LogLevel l)    { this.level = l;     return this; }
        public Builder service(String s)    { this.service = s;   return this; }
        public Builder message(String m)    { this.message = m;   return this; }
        public Builder field(String k, String v) { this.fields.put(k, v); return this; }
        public LogEvent build() { return new LogEvent(this); }
    }

    @Override public String toString() {
        return String.format("%-5s %-9s %-16s %s", level, service, message, fields);
    }
}
