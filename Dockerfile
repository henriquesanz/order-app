# Step 1: Use an official Maven image to build the application
FROM maven:3.9.1-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies (without copying the full source code yet)
COPY pom.xml .

# Download the dependencies
RUN mvn clean install -DskipTests

# Step 2: Copy the source code into the container
COPY src ./src

# Step 3: Build the application
RUN mvn package -DskipTests

# Step 4: Create a new image from the OpenJDK image for running the application
FROM eclipse-temurin:17-jre-alpine

# Set the working directory for running the application
WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/OrderApp-0.0.1.jar /app/OrderApp.jar

# Expose the port that your application will listen on (default Spring Boot port is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "OrderApp.jar"]