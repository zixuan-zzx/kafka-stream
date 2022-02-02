package me.zixuan.clientskt.ymlconfig.controller

import me.zixuan.clientskt.ymlconfig.service.KafkaProducerService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/kafka"])
class KafkaProducerController(val kafkaProducerService: KafkaProducerService) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping(value = ["/publish"])
    fun sendMessageToKafkaTopic(@RequestParam("message") message: String) {
        kafkaProducerService.sendMessage(message)
        logger.info("Sending message using KafkaProducerService: {}", message)
    }
}