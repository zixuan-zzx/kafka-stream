package me.zixuan.hellostreams.processor;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;

// Generics referring to input key, value and output key, value types
public class SayHelloProcessor implements Processor<Void, String, Void, Void> {

    @Override
    // output key, value types
    public void init(ProcessorContext<Void, Void> context) {
        // no special initialization needed
    }

    @Override
    // input key, value types
    public void process(Record<Void, String> record) {
        // contains processing logic
        System.out.println("(Processor API) Hello, " + record.value());
    }

    @Override
    public void close() {
        // no special clean up needed
    }
}
