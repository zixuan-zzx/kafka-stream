package me.zixuan.clients.ymlconfig.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class KafkaConsumerService {
    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    public void consume(String message) {
        log.info(String.format("Received message from Kafka topic: %s", message));
    }
}
