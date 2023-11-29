#!/bin/bash
redis-cli -h $REDIS_HOST -p $REDIS_PORT \
	--user $REDIS_USER --pass $REDIS_PASSWORD
