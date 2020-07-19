#!/bin/bash
dirname=$1
export LOG_DIR_NAME=$dirname

cd ~/Downloads/elasticsearch-7.5.2

./bin/elasticsearch -d

cd ~/Downloads/logstash-7.5.2

sudo ./bin/logstash -f ./../logstash/pipeline/logstash.conf