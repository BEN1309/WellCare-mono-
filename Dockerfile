# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Set working directory inside container
WORKDIR /app

# Set the actual JAR file name (case-sensitive!)
ARG JAR_FILE=target/WellCare-0.0.1-SNAPSHOT.jar

# Copy JAR into the container
COPY ${JAR_FILE} app.jar

# Expose your app's port
EXPOSE 8888

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]
