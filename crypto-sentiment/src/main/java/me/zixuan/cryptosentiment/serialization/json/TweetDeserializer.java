package me.zixuan.cryptosentiment.serialization.json;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.zixuan.cryptosentiment.serialization.Tweet;
import org.apache.kafka.common.serialization.Deserializer;

public class TweetDeserializer implements Deserializer<Tweet> {
    private final Gson gson =
            new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();

    @Override
    public Tweet deserialize(String topic, byte[] bytes) {
        if (bytes == null) return null;
        return gson.fromJson(new String(bytes), Tweet.class);
    }
}
