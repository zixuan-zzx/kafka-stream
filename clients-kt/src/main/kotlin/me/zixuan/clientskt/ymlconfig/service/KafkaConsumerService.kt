package me.zixuan.clientskt.ymlconfig.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumerService {
    private val logger = LoggerFactory.getLogger(javaClass)
    @KafkaListener(topics = ["\${spring.kafka.template.default-topic}"])
    fun consume(message: String) {
        logger.info("Received message from Kafka topic: {}", message)
    }
}