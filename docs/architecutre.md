# Architecture

## Initial Module Structure

The project is organized as a Maven multi-module project:

- `application` - Open Liberty application containing domain, application, management, and infrastructure concerns.
- `jmx-client` - Standalone Java application used to interact with remote JMX MBeans.

## Application Package Structure

```text
com.jmxlab
├── domain
├── application
├── management
└── infrastructure
