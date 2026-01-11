# Use official Maven image to build the project
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory
WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build the project
RUN mvn clean package -DskipTests

# Use a minimal Java runtime image
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copy the jar from the build stage
COPY --from=build /app/target/credit-simulator-1.0-SNAPSHOT.jar ./app.jar

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
