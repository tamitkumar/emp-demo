# Start with a base image that includes OpenJDK (Alpine Linux with OpenJDK 17)
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged jar file into the container at /app
COPY target/employee-jpa-tutorial-v1.jar /app/employee-jpa-tutorial-v1.jar

# Specify the command to run your application
CMD ["java", "-jar", "/app/employee-jpa-tutorial-v1.jar"]