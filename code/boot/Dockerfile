FROM openjdk:21-jdk

WORKDIR /app

COPY target/boot-0.0.1-SNAPSHOT.jar tenpoChallenge.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tenpoChallenge.jar"]