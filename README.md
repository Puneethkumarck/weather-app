# weather-app
Read the lat &amp; lon from text file post request into kafka queue ,kafka listener will process request and makes http call to weather details and stores the data in cassandra .

confluent-kafka in windows
==========================
- zookeeper-server-start.bat ../../etc/kafka/zookeeper.properties  --> Starting zookeeper
- kafka-server-start.bat ../.. /etc/kafka/server.properties --> Starting kafka broker
- kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 5 --topic weather_topic --> create kafka topic

