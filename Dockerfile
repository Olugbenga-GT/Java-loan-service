#Start with a base image containing Java runtime
FROM openjdk:28-ea-17-jdk-slim


# Add the application's jar to the image
COPY target/loanservice-0.0.1-SNAPSHOT.jar loanservice-0.0.1-SNAPSHOT.jar


# execute the application
ENTRYPOINT ["java", "-jar", "loanservice-0.0.1-SNAPSHOT.jar"]
