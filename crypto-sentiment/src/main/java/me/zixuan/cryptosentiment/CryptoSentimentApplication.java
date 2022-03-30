package me.zixuan.cryptosentiment;

import lombok.val;
import me.zixuan.cryptosentiment.serialization.Tweet;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;

import java.util.Properties;

public class CryptoSentimentApplication {
    public static void main(String[] args) {
        val topology = CryptoTopology.build();

        val config = new Properties();
        // kafka streams application must set an application-id
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // NOTE: set exception handlers to handle poisonous pills
        config.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                new LogAndContinueExceptionHandler());
        config.put(StreamsConfig.DEFAULT_PRODUCTION_EXCEPTION_HANDLER_CLASS_CONFIG,
                new LogAndContinueExceptionHandler());

        val streams = new KafkaStreams(topology, config);

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        System.out.println("Starting Twitter streams");
        streams.start(); // non-blocking call, the topology is executed via background threads
    }
}
