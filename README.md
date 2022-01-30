# kafka Stream

This repo demonstrates the usage of Kafka's Clients API (Producer & Consumer) and Streams API.

## Running Locally in Docker

Prerequisite: docker-compose

### Start the Kafka broker

```shell
docker-compose up -d
```

### Exec into the container

```shell
docker-compose exec broker bash
```

### Create a topic

```shell
# inside the container
kafka-topics --bootstrap-server broker:9092 \
             --create \
             --topic test
```

### Produce messages

```shell
# inside the container
kafka-console-producer --bootstrap-server broker:9092 \
                       --topic test
```

### Consume messages

```shell
# inside the container
kafka-console-consumer --bootstrap-server broker:9092 \
                       --topic test \
                       --from-beginning
```

### Stop the Kafka broker

```shell
docker-compose down
```

### Reference

Running Kafka brokers in the [Confluent Clout](https://developer.confluent.io/quickstart/kafka-on-confluent-cloud/)
, [docker environment](https://developer.confluent.io/quickstart/kafka-docker/)
, [local environment](https://developer.confluent.io/quickstart/kafka-local/).