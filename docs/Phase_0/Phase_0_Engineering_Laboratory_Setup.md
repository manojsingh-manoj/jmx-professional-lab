# JMX Professional Lab --- Phase 0 Reference Guide

## Purpose

This document is the complete reference for **Phase 0 --- Engineering
Laboratory Setup** of the JMX Professional Lab.

The goal of Phase 0 is to establish a professional, reproducible
Java/Open Liberty/Docker development foundation before introducing JMX.

The guiding principle is:

> Build and verify the runtime independently before adding management
> complexity.

------------------------------------------------------------------------

# Phase 0 --- What We Build

By the end of Phase 0:

``` text
                         GitHub
                           │
                           ▼
                 jmx-professional-lab
                           │
              ┌────────────┴────────────┐
              │                         │
              ▼                         ▼
           Maven                    Docker
              │                         │
              ▼                         ▼
       application.war          Liberty image
                                        │
                                        ▼
                                  Docker Compose
                                        │
                                        ▼
                               ┌────────────────┐
                               │    Liberty     │
                               │                │
                               │ HTTP :9080     │
                               │ Healthy        │
                               └────────────────┘
```

The application can be run:

1.  Locally through Maven + Open Liberty.
2.  Inside a Docker container.
3.  Through Docker Compose.

The repository is version-controlled in GitHub, and a `phase-0-baseline`
tag marks the known-good infrastructure state.

------------------------------------------------------------------------

# Phase 0 Roadmap

## Step 0.1 --- Define the Engineering Project

### Objective

Define the purpose, scope, architecture direction, and engineering
standards of the JMX lab.

### Project goal

Build a professional Java application running on Open Liberty and
expose/manage application runtime state through JMX.

The lab will eventually include:

``` text
JMX Client
    │
    │ JMX/RMI
    ▼
Open Liberty
    │
    ▼
MBeanServer
    │
    ▼
Application MBeans
    │
    ▼
Application runtime
```

### Engineering principles

-   Learn concepts before coding.
-   Work in small verified steps.
-   Keep build/runtime responsibilities separate.
-   Version everything meaningful in Git.
-   Commit at meaningful milestones.
-   Prefer reproducibility over manual configuration.
-   Diagnose failures layer-by-layer.
-   Treat Docker and JMX networking as architectural concerns.
-   Use Open Liberty's supported configuration mechanisms.

### Completion marker

``` text
Step 0.1 Define the Engineering Project complete
```

------------------------------------------------------------------------

# Step 0.2 --- Development Environment Verification

### Objective

Verify that the local development machine has the required tooling.

### Environment used

-   macOS
-   Apple Silicon / `aarch64`
-   IntelliJ IDEA Ultimate
-   Java 21
-   Maven 3.9.x
-   Git
-   Docker Desktop
-   Docker Compose

### Java verification

``` bash
java -version
javac -version
```

Expected Java family:

``` text
Java 21 LTS
```

### Maven verification

``` bash
mvn -version
```

Verify Maven is using Java 21.

### Git verification

``` bash
git --version
git config --global user.name
git config --global user.email
```

### Docker verification

``` bash
docker --version
docker compose version
docker info
```

### Environment variable

``` bash
echo $JAVA_HOME
```

It should point to the Java 21 installation.

### Completion marker

``` text
Step 0.2 Development Environment Verification complete
```

------------------------------------------------------------------------

# Step 0.3 --- Create the GitHub Repository

### Objective

Create the remote repository before substantial implementation begins.

Repository:

``` text
jmx-professional-lab
```

### Local repository initialization

The project was initialized as a Git repository and connected to GitHub.

Typical remote:

``` bash
git remote add origin git@github.com:<username>/jmx-professional-lab.git
```

### Initial branch

Use:

``` text
main
```

### First push

``` bash
git push -u origin main
```

### Important principle

The GitHub repository is the permanent engineering record of the lab.

### Completion marker

``` text
Step 0.3 Create the GitHub Repository complete
```

------------------------------------------------------------------------

# Step 0.4 --- Configure GitHub SSH Authentication

Step 0.4 was intentionally divided into smaller steps.

## Step 0.4a --- Generate SSH Key

### Check existing SSH directory

``` bash
ls -la ~/.ssh
```

### Generate an ED25519 key

The lab uses an ED25519 SSH key.

Verify its fingerprint:

``` bash
ssh-keygen -lf ~/.ssh/id_ed25519.pub
```

Example output:

``` text
256 SHA256:... manojsingh.manoj@gmail.com (ED25519)
```

### Completion marker

``` text
Step 0.4a Generate SSH Key complete
```

------------------------------------------------------------------------

## Step 0.4b --- Add SSH Public Key to GitHub

The public key was added to the GitHub account.

The public key is:

``` text
~/.ssh/id_ed25519.pub
```

Never share:

``` text
~/.ssh/id_ed25519
```

The private key must remain private.

### Completion marker

``` text
Step 0.4b Add SSH Public Key to GitHub complete
```

------------------------------------------------------------------------

## Step 0.4c --- Configure macOS SSH Agent and Test Authentication

The SSH agent was configured to use the ED25519 key.

GitHub authentication was tested using SSH.

The purpose was to establish:

``` text
local Git
    │
    │ SSH
    ▼
GitHub
```

### Completion marker

``` text
Step 0.4c Configure macOS SSH Agent and Test GitHub Authentication complete
```

------------------------------------------------------------------------

## Step 0.4d --- Git Repository Health Check

Verify:

``` bash
git status
git remote -v
git log --oneline
```

The repository should have:

-   correct GitHub remote
-   correct branch
-   valid Git identity
-   working SSH authentication
-   clean or understood working tree

### Completion marker

``` text
Step 0.4d Git Repository Health Check complete
```

------------------------------------------------------------------------

# Step 0.5 --- Create the Maven Multi-Module Project

### Objective

Establish Maven as the build system and create the multi-module project
foundation.

Conceptually:

``` text
jmx-professional-lab/
│
├── pom.xml
│
├── application/
│   └── pom.xml
│
└── jmx-client/
    └── pom.xml
```

The root POM manages modules and common project configuration.

### Why multi-module?

The eventual architecture has at least two logical applications:

``` text
application
    │
    └── Liberty application


jmx-client
    │
    └── Standalone JMX client
```

Keeping them separate prevents the management client from becoming
coupled to the application runtime.

### Completion marker

``` text
Step 0.5 Create the Maven Multi-Module Project complete
```

------------------------------------------------------------------------

# Step 0.6 --- Establish the Project Package and Directory Architecture

### Objective

Create a professional repository layout before implementation grows.

Conceptual layout:

``` text
jmx-professional-lab/
│
├── pom.xml
├── .gitignore
│
├── application/
│   ├── pom.xml
│   └── src/
│       ├── main/
│       │   ├── java/
│       │   └── liberty/
│       │       └── config/
│       │           └── server.xml
│       └── test/
│
├── jmx-client/
│   ├── pom.xml
│   └── src/
│       └── main/
│           └── java/
│
├── docker/
│   └── liberty/
│       └── Dockerfile
│
└── docs/
```

### Architectural rule

Keep these responsibilities separate:

``` text
application/
    application behavior

jmx-client/
    management client

docker/
    container packaging

compose.yaml
    runtime orchestration

docs/
    engineering documentation
```

### Completion marker

``` text
Step 0.6 Establish the Project Package and Directory Architecture complete
```

------------------------------------------------------------------------

# Step 0.7 --- Create the First Java Application Skeleton

### Objective

Create the first working Java application that will later become the
subject of JMX management.

The initial application was intentionally simple.

The runtime component is an `OrderProcessor`-style service holding
in-memory runtime state such as:

``` text
processedOrders
```

The application also exposes an HTTP endpoint so that the runtime can be
verified independently of JMX.

### Key principle

Before introducing JMX, establish a normal application that works
without JMX.

That gives us a control case:

``` text
Application works
        ↓
Add JMX
        ↓
If something breaks, investigate JMX
```

### Completion marker

``` text
Step 0.7 Create the First Java Application Skeleton complete
```

------------------------------------------------------------------------

# Step 0.8 --- Configure IntelliJ IDEA

### Objective

Make IntelliJ IDEA the primary development environment.

Configure:

-   project SDK
-   Java 21
-   Maven integration
-   Maven project import
-   source directories
-   module structure
-   application module
-   JMX client module

### Important principle

The IDE is a development tool, not part of the runtime.

The runtime should remain independently reproducible using:

``` text
Maven
Docker
Docker Compose
```

### Completion marker

``` text
Step 0.8 Configure IntelliJ IDEA complete
```

------------------------------------------------------------------------

# Step 0.9 --- Open Liberty Foundation

Step 0.9 established the Liberty runtime and was broken into multiple
substeps.

------------------------------------------------------------------------

## Step 0.9a --- Understand Open Liberty Architecture

### Objective

Understand what Liberty provides.

Conceptually:

``` text
JVM
 │
 ▼
Open Liberty
 │
 ├── runtime
 ├── features
 ├── configuration
 └── applications
```

Liberty is feature-oriented.

The application declares required capabilities through `server.xml`.

Example:

``` xml
<featureManager>
    <feature>servlet-6.1</feature>
</featureManager>
```

### Key principle

Do not treat Liberty as just a generic Java process.

Liberty has:

-   runtime
-   features
-   server configuration
-   application lifecycle
-   management infrastructure

These concepts become important when we introduce JMX.

### Completion marker

``` text
Step 0.9a Understand Open Liberty Architecture complete
```

------------------------------------------------------------------------

# Step 0.9b --- Choose the Liberty Application Model

### Objective

Decide how the application is packaged and deployed.

The project uses:

``` text
Maven
  ↓
WAR
  ↓
Open Liberty
```

We are using the Open Liberty Maven Plugin for development and packaging
integration.

### Important clarification

Using the Open Liberty Maven guidance does NOT mean that the final
architecture will remain an embedded Liberty runtime on the host.

We use Maven + Liberty Plugin for development.

Later:

``` text
Docker
  ↓
Open Liberty container
  ↓
WAR
```

So:

``` text
Development
    Maven + Liberty

Runtime
    Docker + Liberty
```

### Completion marker

``` text
Step 0.9b Choose the Liberty Application Model complete
```

------------------------------------------------------------------------

# Step 0.9c --- Configure the Liberty Maven Plugin

### Objective

Configure the Open Liberty Maven Plugin in the application module.

The plugin used was:

``` xml
<plugin>
    <groupId>io.openliberty.tools</groupId>
    <artifactId>liberty-maven-plugin</artifactId>
    <version>3.11.5</version>
</plugin>
```

The plugin enables development workflows such as:

``` bash
mvn liberty:dev
```

### Why use it?

It provides a convenient local development lifecycle:

``` text
Maven
  ↓
Liberty
  ↓
Deploy application
  ↓
Watch/recompile
```

### Completion marker

``` text
Step 0.9c Configure the Liberty Maven Plugin complete
```

------------------------------------------------------------------------

# Step 0.9d --- Create the Open Liberty server.xml

### Objective

Create the Liberty server configuration.

The important configuration includes the Servlet feature and HTTP
endpoint.

Conceptually:

``` xml
<server>
    <featureManager>
        <feature>servlet-6.1</feature>
    </featureManager>

    <httpEndpoint
        id="defaultHttpEndpoint"
        httpPort="9080"
        httpsPort="9443" />

    <webApplication
        id="application"
        name="application"
        location="application.war"
        contextRoot="/jmx-lab" />
</server>
```

The exact file in the project is:

``` text
application/src/main/liberty/config/server.xml
```

### Important architecture decision

There is only **one source-of-truth `server.xml`**.

We do not maintain a Docker-specific duplicate.

Docker copies:

``` text
application/src/main/liberty/config/server.xml
```

directly into the image.

### Completion marker

``` text
Step 0.9d Create the Open Liberty server.xml complete
```

------------------------------------------------------------------------

# Step 0.9e --- Liberty Deployment and Context Root

### Objective

Verify WAR deployment and explicitly control the HTTP context root.

The desired URL is:

``` text
http://localhost:9080/jmx-lab/
```

rather than an automatically derived application name such as:

``` text
/application-0.1.0-SNAPSHOT/
```

The context root is configured in `server.xml` through the web
application configuration.

Example:

``` xml
<webApplication
    id="application"
    name="application"
    location="application.war"
    contextRoot="/jmx-lab" />
```

### Lesson

Do not rely on Maven artifact names to determine your API URL.

Application deployment identity and HTTP context root should be
explicitly controlled.

### Completion marker

``` text
Step 0.9e Liberty deployment and context root complete
```

------------------------------------------------------------------------

# Step 0.9f --- Create the First HTTP Endpoint

### Objective

Create a simple HTTP endpoint to prove that the Liberty application
works.

Endpoint:

``` text
/jmx-lab/api/orders/status
```

Example verification:

``` bash
curl -i http://localhost:9080/jmx-lab/api/orders/status
```

Expected result:

``` text
HTTP/1.1 200 OK
```

with runtime information such as:

``` text
processedOrders=0
```

### Architecture

``` text
HTTP
  ↓
Servlet
  ↓
OrderProcessor
  ↓
runtime state
```

### Why HTTP before JMX?

Because JMX should not be responsible for proving that the application
works.

We first prove:

``` text
Liberty works
Application works
HTTP works
```

Then:

``` text
JMX works
```

### Completion marker

``` text
Step 0.9f Create the First HTTP Endpoint complete
```

------------------------------------------------------------------------

# Step 0.9g --- Verify Liberty Application Lifecycle and Graceful Shutdown

### Objective

Understand application/server lifecycle before containerization.

Start:

``` bash
mvn liberty:dev
```

Wait for:

``` text
CWWKF0011I
```

Verify:

``` bash
curl -i http://localhost:9080/jmx-lab/api/orders/status
```

Then gracefully stop Liberty by typing:

``` text
q
```

Verify the endpoint is no longer available.

Start Liberty again and verify the endpoint works again.

### Lifecycle model

``` text
START
  ↓
Initialize runtime
  ↓
Deploy application
  ↓
READY
  ↓
RUNNING
  ↓
STOP
  ↓
Application shutdown
  ↓
Liberty stopped
```

### Important observation

The application runtime state is in memory.

For example:

``` text
processedOrders
```

returns to its initial value after JVM/application restart.

This is important for later JMX work:

> JMX manipulates live runtime state; JMX itself is not persistence.

### Completion marker

``` text
Step 0.9g Verify Liberty Application Lifecycle and Graceful Shutdown complete
```

------------------------------------------------------------------------

# Step 0.9h --- Prepare and Verify Containerized Liberty

Step 0.9h is the Docker/Compose portion of Phase 0.

------------------------------------------------------------------------

## Step 0.9h.1 --- Understand the Container Boundary

Before Docker:

``` text
macOS
├── Java
├── Maven
├── IntelliJ
└── Open Liberty
```

Containerized:

``` text
macOS
└── Docker
    └── Open Liberty
        ├── JVM
        └── application.war
```

The runtime container should NOT contain:

``` text
Maven
IntelliJ
Git
Java source
tests
```

The container contains runtime concerns.

------------------------------------------------------------------------

## Step 0.9h.2 --- Separate Build Time and Runtime

Build:

``` text
Java source
   ↓
Maven
   ↓
application.war
```

Runtime:

``` text
application.war
   ↓
Docker image
   ↓
Open Liberty
   ↓
Container
```

This prevents Maven from becoming part of the runtime image.

------------------------------------------------------------------------

## Step 0.9h.3 --- Choose the Liberty Base Image

The selected image is:

``` text
icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal
```

It supports:

``` text
linux/amd64
linux/arm64
linux/ppc64le
linux/s390x
```

The user's Apple Silicon machine uses:

``` text
linux/arm64
```

Docker therefore selects the appropriate ARM64 image automatically.

### Why `kernel-slim`?

It is a minimal Liberty runtime.

It does not automatically contain every Liberty feature.

That gives us a deliberate, minimal runtime image.

------------------------------------------------------------------------

## Step 0.9h.4 --- Create the Docker Build Context

The Docker build context is the repository root.

Important files:

``` text
application/src/main/liberty/config/server.xml
application/target/application.war
docker/liberty/Dockerfile
```

The Dockerfile can access them because the build context is:

``` text
.
```

from the repository root.

### Important lesson

Dockerfile location does not define the build context.

The build command does.

------------------------------------------------------------------------

## Step 0.9h.5 --- Design the Dockerfile

### Final Dockerfile

``` dockerfile
FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal

COPY application/src/main/liberty/config/server.xml /config/server.xml

RUN features.sh

COPY application/target/application.war /config/apps/application.war

EXPOSE 9080 9443
```

### Why `features.sh`?

The `kernel-slim` image does not contain every Liberty feature.

Our `server.xml` declares:

``` xml
<feature>servlet-6.1</feature>
```

`features.sh` reads the configuration and installs the required Liberty
features.

The order matters:

``` text
FROM kernel-slim
       ↓
COPY server.xml
       ↓
RUN features.sh
       ↓
servlet-6.1 installed
       ↓
COPY WAR
```

### Important production lesson

Use the runtime's supported mechanisms instead of manually installing
arbitrary runtime dependencies.

------------------------------------------------------------------------

# `.dockerignore`

The repository uses a `.dockerignore` similar to:

``` text
.git
.gitignore
.idea
*.iml

application/src/main/java
application/src/test
application/.idea

**/target/*
!application/target/application.war

docs
```

The important exception is:

``` text
!application/target/application.war
```

because the WAR is intentionally required by the Docker build.

The Liberty configuration remains available:

``` text
application/src/main/liberty/config/server.xml
```

------------------------------------------------------------------------

# Step 0.9h.6 --- Build the Open Liberty Docker Image

### Build command

``` bash
docker build \
  -f docker/liberty/Dockerfile \
  -t jmx-professional-lab:0.1.0 \
  .
```

### Verify image

``` bash
docker images jmx-professional-lab
```

### Verify architecture

``` bash
docker image inspect jmx-professional-lab:0.1.0 \
  --format '{{.Architecture}}/{{.Os}}'
```

Expected on Apple Silicon:

``` text
arm64/linux
```

### Verify exposed ports

``` bash
docker image inspect jmx-professional-lab:0.1.0 \
  --format '{{json .Config.ExposedPorts}}'
```

Expected conceptually:

``` text
{"9080/tcp":{},"9443/tcp":{}}
```

### Inspect layers

``` bash
docker history jmx-professional-lab:0.1.0
```

### Verify WAR inside image

Create a temporary container:

``` bash
docker create --name jmx-lab-inspect jmx-professional-lab:0.1.0
```

Copy the WAR out:

``` bash
docker cp \
  jmx-lab-inspect:/config/apps/application.war \
  /tmp/jmx-lab-application.war
```

Verify:

``` bash
ls -lh /tmp/jmx-lab-application.war
```

Remove:

``` bash
docker rm jmx-lab-inspect
```

### Verify server.xml

``` bash
docker create --name jmx-lab-inspect jmx-professional-lab:0.1.0

docker cp \
  jmx-lab-inspect:/config/server.xml \
  /tmp/jmx-lab-server.xml

diff \
  application/src/main/liberty/config/server.xml \
  /tmp/jmx-lab-server.xml

docker rm jmx-lab-inspect
```

Expected `diff` output:

``` text
(no output)
```

This proves the image contains the same configuration as the source
tree.

------------------------------------------------------------------------

# Step 0.9h.7 --- Run Liberty Container and Verify Application

### Run

``` bash
docker run \
  --name jmx-professional-lab \
  -p 9080:9080 \
  jmx-professional-lab:0.1.0
```

### Port mapping

``` text
Mac :9080
    ↓
Docker :9080
    ↓
Liberty :9080
```

### Verify

``` bash
curl -i http://localhost:9080/jmx-lab/api/orders/status
```

Expected:

``` text
HTTP/1.1 200 OK
```

and:

``` text
processedOrders=0
```

### Verify container

``` bash
docker ps
```

### Verify port

``` bash
docker port jmx-professional-lab
```

Expected:

``` text
9080/tcp -> 0.0.0.0:9080
```

### Logs

``` bash
docker logs jmx-professional-lab
```

### Stop

``` bash
docker stop jmx-professional-lab
```

### Start again

``` bash
docker start jmx-professional-lab
```

### Remove

``` bash
docker stop jmx-professional-lab
docker rm jmx-professional-lab
```

The image remains:

``` bash
docker images jmx-professional-lab
```

### Important distinction

``` text
IMAGE
  │
  ├── Container A
  ├── Container B
  └── Container C
```

An image is the packaged runtime.

A container is an instance of that image.

------------------------------------------------------------------------

# Step 0.9h.8 --- Introduce Docker Compose

### Objective

Replace a long manual `docker run` command with version-controlled
runtime configuration.

Create:

``` text
compose.yaml
```

Initial structure:

``` yaml
services:

  liberty:
    image: jmx-professional-lab:0.1.0

    container_name: jmx-professional-lab

    ports:
      - "9080:9080"
```

### Important distinction

``` text
Dockerfile
    ↓
HOW to build image


compose.yaml
    ↓
HOW to run services
```

### Validate

``` bash
docker compose config
```

### Start

``` bash
docker compose up
```

### Test

``` bash
curl -i http://localhost:9080/jmx-lab/api/orders/status
```

### Detached mode

``` bash
docker compose up -d
```

### Status

``` bash
docker compose ps
```

### Logs

``` bash
docker compose logs liberty
```

### Stop/remove

``` bash
docker compose down
```

### Compose networking lesson

Compose creates a project network automatically.

Later, if we have:

``` text
liberty
jmx-client
```

the JMX client can address the Liberty service by service name, e.g.:

``` text
liberty
```

rather than using `localhost`.

Inside a container:

``` text
localhost
```

means:

> this container

It does not mean:

> another Compose service.

This distinction is critical for JMX.

------------------------------------------------------------------------

# Step 0.9h.9 --- Container Health and Runtime Verification

### Objective

Distinguish:

``` text
container running
```

from:

``` text
application ready
```

### Healthcheck

The Compose service was extended with:

``` yaml
healthcheck:
  test:
    [
      "CMD",
      "curl",
      "-f",
      "http://localhost:9080/jmx-lab/api/orders/status"
    ]
  interval: 10s
  timeout: 5s
  retries: 5
  start_period: 10s
```

### Lifecycle

``` text
Container created
       ↓
Container running
       ↓
Liberty starting
       ↓
Application deploying
       ↓
HTTP endpoint responds
       ↓
Container healthy
```

### Verify

``` bash
docker compose up -d
docker compose ps
```

Initially:

``` text
Up (health: starting)
```

Eventually:

``` text
Up (healthy)
```

### Inspect health

``` bash
docker inspect jmx-professional-lab \
  --format '{{json .State.Health}}'
```

### Manual inside-container test

``` bash
docker exec jmx-professional-lab \
  curl -f http://localhost:9080/jmx-lab/api/orders/status
```

### Important lesson

A health check does not automatically restart a container.

Health status and restart policy are separate concepts.

### Future JMX lesson

An HTTP health check does not necessarily prove:

``` text
JMX connector ready
MBean registered
JMX authentication ready
```

We will eventually design JMX-specific readiness.

------------------------------------------------------------------------

# Step 0.9h.10 --- Finalize Phase 0 Runtime Architecture and Docker/Compose Baseline

### Objective

Perform a final clean-room verification and create a permanent Git
baseline.

### Verify Maven

``` bash
docker compose down
mvn clean verify
```

### Verify WAR

``` bash
ls -lh application/target/application.war
jar tf application/target/application.war | head -30
```

### Rebuild Docker image without cache

``` bash
docker build \
  --no-cache \
  -f docker/liberty/Dockerfile \
  -t jmx-professional-lab:0.1.0 \
  .
```

This proves the Docker image does not depend on accidental local build
cache.

### Start Compose

``` bash
docker compose up -d
```

### Verify

``` bash
docker compose ps
```

Expect:

``` text
healthy
```

### HTTP verification

``` bash
curl -i http://localhost:9080/jmx-lab/api/orders/status
```

Expected:

``` text
HTTP/1.1 200 OK
```

and:

``` text
processedOrders=0
```

### Logs

``` bash
docker compose logs liberty
```

Look for:

``` text
CWWKF0011I
```

and ensure the previous missing-feature error is absent:

``` text
CWWKF0001E
```

------------------------------------------------------------------------

# Phase 0 Git Baseline

### Verify Git

``` bash
git status
git log --oneline --decorate -10
```

### Create annotated tag

``` bash
git tag -a phase-0-baseline \
  -m "Phase 0 baseline: Liberty application and Docker runtime"
```

### Verify

``` bash
git tag
```

Expected:

``` text
phase-0-baseline
```

### Inspect

``` bash
git show phase-0-baseline --no-patch
```

### Push

``` bash
git push origin phase-0-baseline
```

The tag represents:

> Known-good Liberty application + Docker + Compose runtime baseline.

------------------------------------------------------------------------

# Phase 0 Final Architecture

At the end of Phase 0:

``` text
                         GitHub
                           │
                           ▼
                 jmx-professional-lab
                           │
             ┌─────────────┴─────────────┐
             │                           │
             ▼                           ▼
          Maven                       Docker
             │                           │
             ▼                           ▼
      application.war              Docker image
                                         │
                                         ▼
                                   Docker Compose
                                         │
                                         ▼
                               ┌──────────────────┐
                               │     Liberty      │
                               │                  │
                               │ JVM / OpenJ9     │
                               │ Liberty          │
                               │ servlet-6.1      │
                               │                  │
                               │ application.war  │
                               │                  │
                               │ HTTP :9080       │
                               └──────────────────┘
```

------------------------------------------------------------------------

# Phase 0 Engineering Lessons

## 1. Build and runtime are different concerns

``` text
Maven → build artifact
Docker → runtime packaging
Compose → runtime orchestration
```

------------------------------------------------------------------------

## 2. Container running is not application ready

``` text
running ≠ ready
```

Health checks make readiness observable.

------------------------------------------------------------------------

## 3. Minimal Liberty images require explicit feature management

`kernel-slim` does not automatically contain every Liberty feature.

The application configuration declares:

``` xml
<feature>servlet-6.1</feature>
```

and:

``` text
features.sh
```

installs the required feature into the image.

------------------------------------------------------------------------

## 4. Keep configuration single-source

The authoritative file is:

``` text
application/src/main/liberty/config/server.xml
```

Docker copies that file into:

``` text
/config/server.xml
```

We intentionally do not maintain a separate Docker-specific copy.

------------------------------------------------------------------------

## 5. Image and container are different

``` text
image
  ↓
container
```

Removing a container does not remove its image.

------------------------------------------------------------------------

## 6. `localhost` is contextual

On the host:

``` text
localhost
```

means the Mac.

Inside a container:

``` text
localhost
```

means that container.

Between Compose services, use the service name.

This will become extremely important for JMX/RMI.

------------------------------------------------------------------------

## 7. `EXPOSE` does not publish a port

``` dockerfile
EXPOSE 9080
```

documents the container port.

Actual host mapping comes from:

``` yaml
ports:
  - "9080:9080"
```

or:

``` bash
docker run -p 9080:9080 ...
```

------------------------------------------------------------------------

# Phase 0 Git Milestones

The important Git history established during Phase 0 includes milestones
such as:

``` text
feat: add Liberty HTTP application endpoint

feat: containerize Liberty application

feat: add Liberty container health check
```

The final baseline is tagged:

``` text
phase-0-baseline
```

The exact earlier commit history may contain additional setup commits.

------------------------------------------------------------------------

# Phase 0 Completion Criteria

Phase 0 is complete when all of these are true:

``` text
[✓] Java 21 verified
[✓] Maven verified
[✓] Git verified
[✓] GitHub repository created
[✓] SSH authentication configured
[✓] Maven multi-module project created
[✓] Project architecture established
[✓] IntelliJ configured
[✓] Liberty architecture understood
[✓] Liberty Maven Plugin configured
[✓] server.xml configured
[✓] Context root configured
[✓] HTTP endpoint created
[✓] Local Liberty lifecycle verified
[✓] ARM64-compatible Liberty image selected
[✓] Dockerfile created
[✓] kernel-slim feature installation understood
[✓] Docker image built
[✓] Liberty container verified
[✓] Docker Compose introduced
[✓] Compose healthcheck implemented
[✓] Container becomes healthy
[✓] HTTP endpoint verified through Compose
[✓] Git checkpoints committed
[✓] Phase 0 baseline tagged
[✓] Phase 0 baseline pushed to GitHub
```

------------------------------------------------------------------------
