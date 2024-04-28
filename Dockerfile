# Use the official Maven image with OpenJDK 17 as the build stage
FROM maven:3.8.5-openjdk-17 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the rest of the application code
COPY src src

# Build the application
RUN mvn clean install -DskipTests

# Create a new stage for the runtime image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build stage to the runtime image
COPY --from=build /app/target/RajPrints-1.0.jar RajPrints-1.0.jar

EXPOSE 8080

# Specify the default command to run the application
ENTRYPOINT ["java", "-jar", "RajPrints-1.0.jar"]