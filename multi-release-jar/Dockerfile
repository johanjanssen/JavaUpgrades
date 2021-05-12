# Build the JAR file and run it on Java 16
FROM maven:3.8.1-openjdk-17-slim AS builder

ADD . /multi-release-jar
WORKDIR /multi-release-jar

RUN mvn package

# Used to force Docker to always run the commands below the ARG instead of using the cache
ARG DISABLE_CACHE
RUN java -jar target/*.jar



# Run the JAR file on Java 8
FROM maven:3.8.1-openjdk-8-slim

COPY --from=builder /multi-release-jar/target/*.jar .

# Used to force Docker to always run the commands below the ARG instead of using the cache
ARG DISABLE_CACHE
RUN java -jar *.jar


# Run the JAR file on Java 11
FROM maven:3.8.1-openjdk-11-slim

COPY --from=builder /multi-release-jar/target/*.jar .

# Used to force Docker to always run the commands below the ARG instead of using the cache
ARG DISABLE_CACHE
RUN java -jar *.jar