package me.zixuan.clients.ymlconfig.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaProducerService {
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info(String.format("Sent message to Kafka topic: %s", message));
        kafkaTemplate.sendDefault(message);
//        kafkaTemplate.send("test", message);
    }

}
