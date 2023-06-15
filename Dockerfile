# Use a base image with Java and Maven installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY ../pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Create a new image with a smaller footprint
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/songs-app-YOTPO-0.0.1-SNAPSHOT.jar .

# Expose the port your application listens on
EXPOSE 8080

# Set the entry point for the container
CMD ["java", "-jar", "songs-app-YOTPO-0.0.1-SNAPSHOT.jar"]





