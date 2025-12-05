# Use Eclipse Temurin JDK 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory in container
WORKDIR /app

# Copy all Java source files to the container
COPY *.java /app/

# Compile all Java files
RUN javac *.java

# Run the application
CMD ["java", "Main"]
