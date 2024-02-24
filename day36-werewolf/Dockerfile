FROM node:21 AS ng-builder

RUN npm i -g @angular/cli

WORKDIR /ngapp

COPY frontend/package*.json .
COPY frontend/angular.json .
COPY frontend/tsconfig.* .
COPY frontend/src src

RUN npm ci && ng build

# /ngapp/dist/frontend/browser/*

# Starting with this Linux server
FROM maven:3-eclipse-temurin-21 AS sb-builder

## Build the application
# Create a directory call /sbapp
# go into the directory cd /app
WORKDIR /sbapp

# everything after this is in /sbapp
COPY werewolf/mvnw .
COPY werewolf/mvnw.cmd .
COPY werewolf/pom.xml .
COPY werewolf/.mvn .mvn
COPY werewolf/src src
COPY --from=ng-builder /ngapp/dist/frontend/browser/ src/main/resources/static

# Build the application
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:21-jdk-bullseye

WORKDIR /app 

COPY --from=sb-builder /sbapp/target/werewolf-0.0.1-SNAPSHOT.jar werewolf.jar

## Run the application
# Define environment variable 
ENV PORT=8080 
ENV SPRING_DATA_MONGODB_URI=mongodb://localhost:27017/werewolf
ENV GAME_INSTANCE_MAX=5 GAME_INSTANCE_DURATION_HOURS=2

# Expose the port
EXPOSE ${PORT}

# Run the program
ENTRYPOINT SERVER_PORT=${PORT} java -jar werewolf.jar
