FROM icr.io/appcafe/open-liberty:kernel-slim-java21-openj9-ubi-minimal

COPY application/src/main/liberty/config/server.xml /config/server.xml

RUN features.sh

COPY application/target/application.war /config/apps/application.war

EXPOSE 9080 9443