FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/internship-0.0.1-SNAPSHOT.jar /app/internship.jar
ENTRYPOINT ["java", "-jar", "internship.jar"]