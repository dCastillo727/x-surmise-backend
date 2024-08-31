FROM openjdk:22-jdk-bookworm
LABEL authors="davidcastilloprieto"

WORKDIR /app

COPY ../../../spring-boot/authorizationserver/target/authorizationserver.jar /app/authorizationserver.jar

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "authorizationserver.jar"]