package me.zixuan.cryptosentiment.serialization.json;

import com.google.gson.Gson;
import me.zixuan.cryptosentiment.serialization.Tweet;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

public class TweetSerializer implements Serializer<Tweet> {
    private Gson gson = new Gson();

    @Override
    public byte[] serialize(String topic, Tweet tweet) {
        if (tweet == null) return null;
        return gson.toJson(tweet).getBytes(StandardCharsets.UTF_8);
    }
}
