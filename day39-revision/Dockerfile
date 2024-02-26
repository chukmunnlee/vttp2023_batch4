FROM node:21 AS builder

RUN npm i -g @angular/cli

WORKDIR /builder

# copy all .json files
COPY *.json .
COPY src src

RUN npm ci && ng build

FROM node:21

RUN npm i -g http-server

WORKDIR /app
COPY --from=builder /builder/dist/day39-revision/browser/ .

ENV PORT=8080

EXPOSE ${PORT}

ENTRYPOINT http-server --port ${PORT}
