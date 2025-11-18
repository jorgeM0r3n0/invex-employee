# Etapa 1: construir el JAR
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen final
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar invex-employee.jar

EXPOSE 9080
ENTRYPOINT ["java", "-jar", "invex-employee.jar"]
