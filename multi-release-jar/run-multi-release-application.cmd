docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:8u362-b09-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:11.0.18_10-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:17.0.6_10-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:19.0.2_7-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
