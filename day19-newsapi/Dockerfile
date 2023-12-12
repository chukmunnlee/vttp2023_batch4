FROM openjdk:21-bookworm AS builder

WORKDIR /src

COPY src src
COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

# compile the Java application
RUN ./mvnw package -Dmvn.test.skip=true

FROM openjdk:21-bookworm 

WORKDIR /app

# copy and rename to app.jar
COPY --from=builder /src/target/newsapi-0.0.1-SNAPSHOT.jar app.jar

ENV PORT=8080
ENV SPRING_REDIS_HOST=localhost SPRING_REDIS_PORT=1234
ENV SPRING_REDIS_DATABASE=0
ENV SPRING_REDIS_USERNAME=default SPRING_REDIS_PASSWORD=abc123
ENV NEWSAPI_KEY=abc123

EXPOSE $PORT

ENTRYPOINT SERVER_PORT=${PORT} java -jar ./app.jar
