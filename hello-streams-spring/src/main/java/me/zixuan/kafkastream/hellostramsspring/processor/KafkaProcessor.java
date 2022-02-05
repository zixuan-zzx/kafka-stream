package me.zixuan.kafkastream.hellostramsspring.processor;

import lombok.val;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class KafkaProcessor {

    @Bean
    public KStream<Void, String> kStream(StreamsBuilder kStreamBuilder) {
        val TOPIC_NAME = "test";
        KStream<Void, String> stream = kStreamBuilder.stream(TOPIC_NAME);
        stream.foreach((key, value) -> System.out.println("Received message: " + value));
        return stream;
    }

}
