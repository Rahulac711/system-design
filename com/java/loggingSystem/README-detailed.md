# Scalable Log Aggregation — Low-Level Design (Java)

A self-contained, runnable LLD of a log aggregation pipeline, built for system-design
interview practice. Single file, no external dependencies, ~170 lines without comments —
writable and explainable in about 40 minutes.

## The pipeline

```
Producer ──> Buffer (partitioned, pub-sub) ──> ConsumerGroup(s)
                                                  │
                                                  ├─ Chain of Responsibility (parse/filter/enrich/redact)
                                                  └─ Sink (Elasticsearch / S3 / ...)
```

Each stage exists to solve one failure mode of the previous one: the producer emits,
the buffer decouples producers from consumers and absorbs spikes, each consumer group
processes independently, and sinks persist. The buffer fans every event out to all
subscribed groups, so the same stream can feed an indexer and an archiver at once.

## Design patterns

| Pattern | Where | Why it's there |
|---|---|---|
| **Builder** | `LogEvent.Builder` | A record with many optional fields, built immutable so it's safe to share across threads and fan out without defensive copying. |
| **Strategy** | `PartitionStrategy` | Partition-assignment policy is swappable (hash-by-key for ordering vs round-robin for spread) without touching the buffer. |
| **Chain of Responsibility** | `LogProcessor` | Parse / filter / enrich / redact are independent, composable stages; any stage can drop a record by returning `null`. |
| **Observer / Pub-Sub** | `LogBuffer` | `publish` fans out to every subscribed consumer group; each keeps an independent copy (models Kafka's independent consumer-group offsets). |
| **Producer-Consumer** | `ConsumerGroup` | Per-partition `BlockingQueue` with one worker thread per partition — parallelism is capped by partition count. |

## Classes

**Data model**
- `LogLevel` — enum (DEBUG < INFO < WARN < ERROR).
- `LogEvent` — immutable record (timestamp, level, service, message, fields). Built via
  `LogEvent.builder()`. Because it's immutable, `withField` returns a new copy rather than mutating.

**Interfaces / abstractions**
- `PartitionStrategy` — `selectPartition(event, count)`. `ServiceKeyPartitioner` hashes by
  service so the same service always lands on the same partition (preserves ordering).
- `LogProcessor` — abstract base for the processing chain; `linkTo` assembles the chain,
  `process` runs it and short-circuits on `null`. Concrete steps: `LevelFilter`, `Enricher`, `Redactor`.
- `LogSink` — `write(event)`. Concrete: `ElasticsearchSink`, `ArchiveSink`.

**Runtime**
- `ConsumerGroup` — owns its own partition queues, a processing chain, and a sink. Starts
  one consumer thread per partition.
- `LogBuffer` — the pub-sub backbone; `subscribe`, `publish` (fan-out), `startAll`, `shutdownAll`.
- `ServiceLogProducer` — source that emits events into the buffer.
- `LogAggregationDemo` — `main`, wires the example flow.

## Run it

Requires JDK 11+ (uses single-file source launch; no separate compile needed).

```bash
java LogAggregationDemo.java
```

Or compile explicitly:

```bash
javac LogAggregationDemo.java && java LogAggregationDemo
```

## Sample output

The `main` flow wires two consumer groups subscribed to the same buffer:
an **indexer** (filter → enrich → redact → Elasticsearch) and an **archiver**
(no chain → S3). Four events are produced.

```
  [S3 raw   ] INFO  auth      login ok         {email=rahul@thg.com}
  [S3 raw   ] DEBUG auth      cache hit        {}
  [S3 raw   ] ERROR payments  gateway timeout  {}
  [S3 raw   ] WARN  catalog   slow query       {}
  [ES index ] INFO  auth      login ok         {email=***, dc=dc-mumbai}
  [ES index ] ERROR payments  gateway timeout  {dc=dc-mumbai}
  [ES index ] WARN  catalog   slow query       {dc=dc-mumbai}
done.
```

What this proves:
- **Fan-out** — the archiver gets all 4 raw events; the indexer processes the same stream independently.
- **Filtering** — the DEBUG line is dropped by the indexer (`LevelFilter(INFO)`) but kept by the archiver.
- **Enrichment** — every indexed event is tagged `dc=dc-mumbai`.
- **Redaction** — the email is masked to `***` on the indexed copy only.
- Output order across partitions/threads is non-deterministic — realistic for a concurrent pipeline.

## Extension points

The pattern choices make each common extension a small, localized change:
- New storage target → implement `LogSink`.
- New processing step → implement `LogProcessor`, `linkTo` it into the chain.
- New partitioning policy → implement `PartitionStrategy`.
- New subscriber → build a `ConsumerGroup` and `subscribe` it; no upstream change.

## Production gaps (raise these proactively in the interview)

This is a teaching skeleton. To make it production-grade you would add:
- **Idempotent dedup** — the model is at-least-once; give each event a deterministic ID
  (content hash, or `partition + offset`) so re-delivered records overwrite instead of duplicate.
- **Backpressure** — swap `LinkedBlockingQueue` for a bounded queue so a slow sink blocks
  producers instead of growing memory unbounded.
- **Dead-letter queue** — route records the chain can't parse to a side topic, not `/dev/null`.
- **Batching + bulk writes** to sinks, and **offset commits** after successful processing.
- **Real broker** (Kafka) in place of the in-memory buffer, with consumer-group rebalancing.