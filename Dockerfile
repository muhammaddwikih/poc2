#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY src/main/resources/application.properties /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
COPY --from=build /home/app/target/poc-0.0.1-SNAPSHOT.jar /app/poc.jar
COPY --from=build /home/app/application.properties /app/application.properties
EXPOSE 8002
ENTRYPOINT ["java","-jar","/app/poc.jar","--spring.config.location=file:/app/application-properties"]