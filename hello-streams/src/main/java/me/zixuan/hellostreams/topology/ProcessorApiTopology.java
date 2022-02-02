package me.zixuan.hellostreams.topology;

import me.zixuan.hellostreams.processor.SayHelloProcessor;
import org.apache.kafka.streams.Topology;

public class ProcessorApiTopology {
    public static Topology build() {
        final Topology topology = new Topology();

        final String TOPIC_NAME = "test";
        final String SOURCE_NAME = "TestTopicSource";

        topology.addSource(SOURCE_NAME, TOPIC_NAME);
        topology.addProcessor("SayHello", SayHelloProcessor::new, SOURCE_NAME);

        return topology;
    }
}
