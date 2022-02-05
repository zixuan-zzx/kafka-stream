# Running Locally in Docker

Prerequisite: docker-compose

## Start the Kafka broker

```shell
docker-compose up -d
```

## Exec into the container

```shell
docker-compose exec broker bash
```

## Create a topic

```shell
# inside the container
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic test
             
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic test \
             --partitions 4 \
             --replication-factor 1
```

## Describe a topic
```shell
kafka-topics --bootstrap-server broker:9092 \
             --describe \
             --topic test
```

## Produce messages

```shell
# inside the container
kafka-console-producer --bootstrap-server broker:9092 \
                       --topic test
                       
kafka-console-producer \
    --bootstrap-server broker:9092 \
    --property key.separator=, \
    --property parse.key=true \
    --topic users
```

## Consume messages

```shell
# inside the container
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic test \
                       --from-beginning
                       
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic test \
                       --property print.timestamp=true \
                       --property print.key=true \
                       --property print.value=true \
                       --from-beginning
```

## Stop the Kafka broker

```shell
docker-compose down
```

## Reference

Running Kafka brokers in the [Confluent Clout](https://developer.confluent.io/quickstart/kafka-on-confluent-cloud/)
, [docker environment](https://developer.confluent.io/quickstart/kafka-docker/)
, [local environment](https://developer.confluent.io/quickstart/kafka-local/).