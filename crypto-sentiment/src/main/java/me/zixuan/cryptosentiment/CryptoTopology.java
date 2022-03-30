package me.zixuan.cryptosentiment;

import lombok.val;
import me.zixuan.cryptosentiment.serialization.Tweet;
import me.zixuan.cryptosentiment.serialization.json.TweetSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

import java.util.Map;

public class CryptoTopology {
    private static final String SOURCE_TOPIC = "tweets";

    public static Topology build() {
        val builder = new StreamsBuilder();

        // Source processor: deserialization
        final KStream<byte[], Tweet> stream =
                builder.stream(
                        SOURCE_TOPIC,
                        Consumed.with(Serdes.ByteArray(), new TweetSerde()));
        stream.print(Printed.<byte[], Tweet>toSysOut().withLabel("tweets-stream"));

        // Filtering
        /* If your application requires a filtering condition, you should filter
        as early as possible. There’s no point in transforming or enriching data
        that will simply be thrown away in a subsequent step, especially if the
        logic for processing the unneeded event is computationally expensive. */
        final KStream<byte[], Tweet> filtered= stream.filterNot(
                (key, tweet) -> tweet.isRetweet());
//        KStream<byte[], Tweet> filtered= stream.filter(
//                (key, tweet) -> !tweet.isRetweet());

        // Branching
        final Predicate<byte[], Tweet> englishTweets =
                (key, tweet) -> tweet.getLang().equals("en");

        final Predicate<byte[], Tweet> nonEnglishTweets =
                (key, tweet) -> !tweet.getLang().equals("en");

        val branchPrefix = "Branch-";
        val englishTweetsBranchName = "EnglishTweets";
        val nonEnglishTweetsBranchName = "NonEnglishTweets";
        val branches = filtered.split(Named.as(branchPrefix))
                .branch(englishTweets, Branched.as(englishTweetsBranchName))
                .branch(nonEnglishTweets, Branched.as(nonEnglishTweetsBranchName))
                .noDefaultBranch();
//                .defaultBranch(Branched.as("OtherLanguageTweets"));

        val englishStream = branches.get(branchPrefix + englishTweetsBranchName);
        val nonEnglishStream = branches.get(branchPrefix + nonEnglishTweetsBranchName);

        // Transforming
        /* Avoid key changes (e.g. rekeying) whenever you can since it will cause an
        internal topic to be created so that rekeyed data will be sent to that topic
        for repartitioning. And then those rekeyed data will be reread back to the
        Kafka streams application, which adds 2 network trip overhead.
        */
//        val translatedStream = nonEnglishStream.map(
//                (key, tweet) -> {
//                    byte[] newKey = tweet.getUsername().getBytes();
//                    Tweet translatedTweet = languageClient.translate(tweet, "en");
//                    return KeyValue.pair(newKey, translatedTweet);
//                });

        val translatedStream = nonEnglishStream.mapValues(
                tweet -> tweet); // TODO: create language client
//                tweet -> languageClient.translate(tweet, "en"));

        // Merging
        val merged = englishStream.merge(translatedStream);

        // Enriching
        val enriched = merged.flatMapValues(
                tweet -> {
                    List<EntitySentiment> results = languageClient.getEntirySentiment(tweet);
                    results.removeIf(entitySentiment -> !currencies.contains(entitySentiment.getEntity()));
                    return results;
                });

        // Sink processor: serialization
        enriched.to("crypto-sentiment",
                Produced.with(
                        Serdes.ByteArray(),
                        AvroSerdes.EntitySentiment("http://localhost:8081", false)));

        return builder.build();
    }
}
