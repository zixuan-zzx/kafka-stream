package me.zixuan.kafkastream.hellostreamsspringkt.processor

import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.KStream
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class KafkaProcessor {
    @Bean
    fun kStream(kStreamsBuilder: StreamsBuilder): KStream<Void, String> {
        val topicName = "test"
        val stream = kStreamsBuilder.stream<Void, String>(topicName)
        stream.foreach { _, value -> println("Received message: $value") }
        return stream
    }
}