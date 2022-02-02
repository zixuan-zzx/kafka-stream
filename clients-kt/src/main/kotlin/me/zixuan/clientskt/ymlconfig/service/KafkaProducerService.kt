package me.zixuan.clientskt.ymlconfig.service

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(val kafkaTemplate: KafkaTemplate<String, String>) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun sendMessage(message: String) {
        kafkaTemplate.sendDefault(message)
        logger.info("Sent message to Kafka topic: {}", message)
    }
}