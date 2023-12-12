FROM golang:1.21-bookworm AS builder

WORKDIR /src

COPY go.mod .
COPY go.sum .
COPY main.go .

RUN CGO_ENABLED=true go build -o main ./...

FROM golang:1.21-bookworm 

WORKDIR /app

COPY --from=builder /src/main /app/main
COPY public public
COPY templates templates

ENV PORT=3000 METRICS_PORT=3100
ENV INSTANCE_NAME="dov bear" INSTANCE_HASH="abc123"

EXPOSE ${PORT} ${METRICS_PORT}

ENTRYPOINT /app/main