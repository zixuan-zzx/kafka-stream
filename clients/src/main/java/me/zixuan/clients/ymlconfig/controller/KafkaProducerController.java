package me.zixuan.clients.ymlconfig.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.zixuan.clients.ymlconfig.service.KafkaProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
@AllArgsConstructor
@Log4j2
public class KafkaProducerController {
    private final KafkaProducerService kafkaProducerService;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        kafkaProducerService.sendMessage(message);
        log.info(String.format("Sending message using KafkaProducerService: %s", message));
    }
}
