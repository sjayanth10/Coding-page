FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Add the application's jar to the container
COPY target/E-learning-website-0.0.1-SNAPSHOT.jar E-learning-website-0.0.1-SNAPSHOT.jar

# Expose the port that the application runs on
EXPOSE 9090

# Run the jar file
ENTRYPOINT ["java", "-jar", "E-learning-website-0.0.1-SNAPSHOT.jar"]