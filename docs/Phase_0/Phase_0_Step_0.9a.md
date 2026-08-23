# Jmx-professional-lab

## Step 0.1 — Define the Engineering Project

Before we touch the terminal, let's establish our baseline. The repository name kept generic because we're going to use this repository for the entire course.

### Project Name
`Jmx-professional-lab`

### Technology Baseline
* **Java:** Core Java, JMX API, Maven
* **Runtime:** Open Liberty
* **Infrastructure:** Docker, Docker Compose
* **Source Control:** Git + Github

---

In this repository I will be maintaining, Code, Documentation, Architecture decisions, Docker Configuration, Tests and Git History.

This means later, if I look at the repository six months from now, I would be able to understand why we made particular architectural decision.

Our final targeted repository would look like below:

<div align="center">

```mermaid
graph TD
    Root[jmx-professional-lab/] --> App[application/]
    App --> AppSrc[src/]
    App --> AppPom[pom.xml]
    
    Root --> Client[jmx-client/]
    Client --> ClientSrc[src/]
    Client --> ClientPom[pom.xml]
    
    Root --> Docker[docker/]
    Docker --> Liberty[liberty/]
    Docker --> DockerClient[client/]
    
    Root --> Docs[docs/]
    Docs --> Arch[architecture/]
    Docs --> Jmx[jmx/]
    Docs --> Dec[decisions/]
    
    Root --> Compose[docker-compose.yml]
    Root --> RootPom[pom.xml]
    Root --> Gitignore[.gitignore]
    Root --> Readme[README.md]
    Root --> License[LICENSE]