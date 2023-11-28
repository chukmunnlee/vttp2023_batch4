# Starting with this Linux server
FROM maven:3-eclipse-temurin-21

## Build the application
# Create a directory call /app
# go into the directory cd /app
WORKDIR /app

# everything after this is in /app
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

# Build the application
RUN mvn package -Dmaven.test.skip=true

## Run the application
# Define environment variable 
ENV PORT=8080 

# Expose the port
EXPOSE ${PORT}

# Run the program
ENTRYPOINT SERVER_PORT=${PORT} java -jar target/day14-0.0.1-SNAPSHOT.jar