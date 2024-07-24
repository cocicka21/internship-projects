FROM maven:latest AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/internship*.jar /app/internship.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/internship.jar"]