#!/bin/bash
#
# mongodb://username:password@the.mongo.server:58205/bgg?authSource=admin 

mongoimport \
	--uri="mongodb://username:password@the.mongo.server:58205/bgg?authSource=admin" \ 
	--drop \
	--db=bgg -c games --type=json --jsonArray \
	--file=/opt/tmp/vttp2023/vttp2023_batch4/day29-revision/bgg/game.json

mongoimport -h the.mongo.server  --port 58205  \
	--username username --password password \
	--authenticationDatabase admin \
	--drop \
	--db=bgg -c comments --type=json --jsonArray \
	--file=/opt/tmp/vttp2023/vttp2023_batch4/day29-revision/bgg/comment.json


