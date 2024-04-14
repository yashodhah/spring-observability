# Important Notes

- interconnection between metrics, logging, and distributed tracing gives you the ability to reason about the state of your system in order to debug exceptions and latency in your applications
- by default spring does a lot of observation by default
- Spring team have been changing the Micrometer API a lot over the course of this year. The most important change that they have introduced a new API: the Observation API.
  - Distributed Tracing -> Micorometer Tracing -> https://www.w3.org/TR/trace-context/
  - Built in Log correlation support
- Micrometer Tracing provides a simple facade for the most popular tracer libraries, letting you instrument your JVM-based application code without vendor lock-in.
- Since we have Micrometer Tracing on the classpath, the logs are automatically correlated (that is, they contain a unique trace identifier). 
