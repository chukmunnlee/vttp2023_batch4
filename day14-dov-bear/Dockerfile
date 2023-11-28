# Base image
FROM node:20

# Create a directory
WORKDIR /app

# Copy the files into the image
COPY main.js .
COPY package-lock.json .
COPY package.json .
COPY public public
COPY views views

# Install the dependencies
RUN npm ci

# Run the application
ENV PORT=3000 INSTANCE_NAME="dov-bear"

EXPOSE ${PORT}

ENTRYPOINT node main.js
