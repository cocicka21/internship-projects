FROM openjdk:21-jdk-slim AS build
WORKDIR /app
COPY .mvn/ .mvn
RUN mvn clean package

FROM azul/zulu-openjdk:21-jre-latest
COPY --from=build /app/target/*.jar /internship.jar
CMD ["java", "-jar", "/internship.jar"]