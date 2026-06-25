# Log Aggregation — LLD (Java)

A runnable, single-file LLD of a log aggregation pipeline for system-design interview prep.
No dependencies; ~170 lines without comments.

## Pipeline

```
Producer -> Buffer (partitioned, pub-sub) -> ConsumerGroup(s) -> Chain (filter/enrich/redact) -> Sink
```

The buffer fans every event out to all subscribed groups, so one stream feeds an indexer
and an archiver independently.

## Patterns

| Pattern | Where | Why |
|---|---|---|
| Builder | `LogEvent` | Immutable record with many optional fields, safe to share across threads. |
| Strategy | `PartitionStrategy` | Swappable partitioning (hash-by-service preserves ordering). |
| Chain of Responsibility | `LogProcessor` | Composable parse/filter/enrich/redact; `null` drops the record. |
| Observer / Pub-Sub | `LogBuffer` | `publish` fans out to all groups; each keeps independent offsets. |
| Producer-Consumer | `ConsumerGroup` | Per-partition `BlockingQueue`, one worker thread per partition. |

## Run

JDK 11+ required.

```bash
java LogAggregationDemo.java
```

The demo wires two groups on one buffer: **indexer** (filter -> enrich -> redact -> Elasticsearch)
and **archiver** (raw -> S3). Result: the archiver keeps all events; the indexer drops DEBUG,
masks the email, and tags each event with a datacenter — same stream, processed independently.

## Extending

New sink -> implement `LogSink`. New step -> implement `LogProcessor` + `linkTo`.
New partitioning -> implement `PartitionStrategy`. New subscriber -> `subscribe` a `ConsumerGroup`.

## Production gaps (mention these in the interview)

At-least-once today. Add: idempotent dedup (deterministic event ID), backpressure (bounded
queue), a dead-letter queue for unparseable records, and batched sink writes.