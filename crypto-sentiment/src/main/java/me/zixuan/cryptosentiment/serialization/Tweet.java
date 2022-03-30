package me.zixuan.cryptosentiment.serialization;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/* If we don’t want to capture certain fields in the source record, then we can
just omit the field from the data class, and Gson will drop it automatically.
Whenever you create your own deserializer, you should consider dropping any fields
in the source record that aren’t needed by your application or downstream applications
during the deserialization process. This process of reducing the available fields to a
smaller subset is called projection and is similar to using a SELECT statement in the
SQL world to only select the columns of interest.*/

/* The data class does not contain all the fields from the JSON object.
 * The process of mapping out only desired fields is called projection.*/
@Data
public class Tweet {
    @SerializedName("CreatedAt")
    private Long createdAt;

    @SerializedName("Id")
    private Long id;

    @SerializedName("Lang")
    private String lang;

    @SerializedName("Retweet")
    private Boolean retweet;

    @SerializedName("Text")
    private String text;

    public boolean isRetweet() {
        return retweet;
    }
}
