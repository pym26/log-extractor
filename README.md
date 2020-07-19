##Overview
The document will help you setting up log extractor tool.

## Prerequisites  
### Required Instances
1. Java 8 version is required
### Required steps before starting actual deployment

1. Download logstash zip and extract it.(https://www.elastic.co/downloads/logstash)
2. Download elasticsearch zip and extract it.(https://www.elastic.co/downloads/elasticsearch)

### To run execute below command
```
    java -jar PATH_TO_JAR_FILE/log-extractor.jar -f "2020-07-18T02:25:11.686Z" -t "2020-07-18T04:25:11.686Z" -i "PATH_LOG_DIRECTORY"
```
