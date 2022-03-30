package me.zixuan.cryptosentiment.serialization.json;

import me.zixuan.cryptosentiment.serialization.Tweet;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class TweetSerde implements Serde<Tweet> {
    @Override
    public Serializer<Tweet> serializer() {
        return new TweetSerializer();
    }

    @Override
    public Deserializer<Tweet> deserializer() {
        return new TweetDeserializer();
    }
}
