# Make sure you are in the Kafka directory before running this command.

# For list all topics
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

# For consume specific topic
bin/kafka-console-consumer.sh --bootstrap-server 127.0.0.1:9092 --from-beginning --topic debezium.public.persons | jq
