FROM maven:3.6.3-openjdk-17-slim AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17-slim
COPY --from=build /target/Lizzy-fileServer-0.0.1-SNAPSHOT.jar  backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","backend.jar"]

