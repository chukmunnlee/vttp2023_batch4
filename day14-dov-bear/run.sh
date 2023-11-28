#!/bin/bash

docker run -d -e PORT=5000 -e INSTANCE_NAME="my app running on port 5000" \
	-p 8082:5000 chukmunnlee/day14-dov-bear:v1.0.0
