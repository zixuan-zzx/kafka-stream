package me.zixuan.hellostreams;

import me.zixuan.hellostreams.topology.DslTopology;
import me.zixuan.hellostreams.topology.ProcessorApiTopology;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class HelloStreamsApplication {
    public static void main(String[] args) {
        // set the required properties for running Kafka Streams
        final Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "dev1");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Void().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // build the topology and start streaming
//        final KafkaStreams streams = new KafkaStreams(DslTopology.build(), config);
        final KafkaStreams streams = new KafkaStreams(ProcessorApiTopology.build(), config);
        streams.start();

        // close Kafka Streams when the JVM shuts down (e.g. SIGTERM)
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));


        // alternatively, use try-with-resources: Any object that implements java.lang.AutoCloseable,
        //      which includes all objects which implement java.io.Closeable, can be used as a resource.
//        try (final KafkaStreams streams = new KafkaStreams(DslTopology.build(), config)) {
//            streams.start();
//            Thread.sleep(1000000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
