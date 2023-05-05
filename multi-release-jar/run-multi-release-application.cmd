docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:8u372-b07-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:11.0.19_7-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:17.0.7_7-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target openjdk:21-slim java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
