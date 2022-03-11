docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:8u322-b06-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:11.0.14.1_1-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
docker run -v /c/Work/JavaUpgrades/multi-release-jar/target:/target eclipse-temurin:17.0.2_8-jre-alpine java -jar /target/multi-release-jar-1.0-SNAPSHOT.jar
