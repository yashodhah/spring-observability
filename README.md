- We need to be proactive on the internal of the system
- Systems are complex today, and it's not just one single sys†me. Often we need to get output from one system and input it into another system. This is where the complexity comes in.


*"how well you can understand the internals of your system by examining its outputs"*.

“handle novel problems (i.e. “unknown unknowns”), and helps us answer the question, “Why is this happening?”

In order to achieve this the application must be properly instrumented. ?

What the heck is that ?

just a fancy name →

application code must emit [signals](https://opentelemetry.io/docs/concepts/signals/) such as [traces](https://opentelemetry.io/docs/concepts/signals/traces/), [metrics](https://opentelemetry.io/docs/concepts/signals/metrics/), and [logs](https://opentelemetry.io/docs/concepts/signals/logs/).

We believe that the interconnection between

- metrics
- logs
- traces

gives you the ability to reason about the state of your system to debug exceptions and latency in your application

# What the heck is Monitoring then ?

Monitoring is a critical core component of observability.

- Monitoring is the process of collecting data and generating reports on different metrics that define system health
- Observability is a more investigative approach.Monitoring collects data on individual components, and observability looks at the distributed system as a whole.

# Back to Observability

Context Propagation

**OpenTelemetry**

OpenTelemetry is an [Observability](https://opentelemetry.io/docs/concepts/observability-primer/#what-is-observability) framework and toolkit designed to create and manage telemetry data such as [traces](https://opentelemetry.io/docs/concepts/signals/traces/), [metrics](https://opentelemetry.io/docs/concepts/signals/metrics/), and [logs](https://opentelemetry.io/docs/concepts/signals/logs/).

OpenTelemetry is focused on the generation, collection, management, and export of telemetry.

The storage and visualization of telemetry are intentionally left to other tools

**OLTP - OpenTelemetry Protocol (OTLP)**

specification describes the encoding, transport, and delivery mechanism of telemetry data between telemetry sources, intermediate nodes such as collectors and telemetry backends.

**Observability backends**

- Jaeger
- Prometheus

**101**

- Logs alone are not so helpful →  as they typically lack contextual information, such as where they were called from.
- Spans →
    - A **span** represents a unit of work or operation.
- Traces → A trace is made of one or more spans.

**Signals**

Traces

Logs

Metrics

Baggage

**Context Propagation**

- correlation of two spans from one service to another?
- propagation - serialize and deserialize the context object and propagate from one to another

# Some example architectures

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/46aa69db-4466-4be9-88d2-fb0272b9e15f/ba042c7b-6877-4f6f-b4bb-940432a0f0f3/Untitled.png)

# Sources of truths

[OpenTelemetry: The Merger of OpenCensus and OpenTracing](https://opensource.googleblog.com/2019/05/opentelemetry-merger-of-opencensus-and.html)

[Project Metrics](https://www.cncf.io/project-metrics/)

[Vendors](https://opentelemetry.io/ecosystem/vendors/)

[Observability, Distributed Tracing & the Complex World • Dave McAllister • GOTO 2019](https://www.youtube.com/watch?v=2nTJSsBngao&t=1870s&ab_channel=GOTOConferences)
