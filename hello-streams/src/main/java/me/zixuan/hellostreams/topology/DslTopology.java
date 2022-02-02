package me.zixuan.hellostreams.topology;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;

public class DslTopology {
    public static Topology build() {
        StreamsBuilder builder = new StreamsBuilder();

        final String TOPIC_NAME = "test";

        // create a KStream source processor reading from "test" topic
        //      with key, value types as Void and String
        KStream<Void, String> stream = builder.stream(TOPIC_NAME);

        stream.foreach(
                (key, value) ->
                        System.out.println("(DSL) Hello, " + value));

        stream.print(Printed.<Void, String>toSysOut().withLabel("DSL"));

        return builder.build();
    }
}
