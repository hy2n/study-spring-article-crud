FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/myapp-0.0.1-SNAPSHOT.jar /app/springapp.jar

ENTRYPOINT ["java", "-jar", "/app/springapp.jar"]