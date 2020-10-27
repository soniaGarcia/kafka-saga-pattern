FROM maven:3.6.3-jdk-11-slim AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn -B -e -C -T 1C org.apache.maven.plugins:maven-dependency-plugin:3.0.2:go-offline
COPY src ./src
RUN ls -la ./src
RUN mvn clean package -Dmaven.test.skip=true
RUN ls -la /app
RUN ls -la /app/target

FROM openjdk:8-jdk-alpine
LABEL maintainer="sonia.guadalupe.garcia@gmail.com"
WORKDIR /workspaces
RUN ls -la /workspaces
#EXPOSE 8080
#RUN ls -la /app
COPY --from=builder /app/target/*.jar app.jar
RUN ls -la /workspaces/app.jar
ENTRYPOINT exec java -jar app.jar