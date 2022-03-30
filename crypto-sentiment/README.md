# About

This module demonstrates using Kafka Streams DSL API to build a stateless processing application with operations such as
filtering, transforming, rekeying, branching, merging, and enriching.

An example record in the source topic:
```json
{
  "CreatedAt":1577933871912,
  "Id":10006,
  "Text":"RT Bitcoin has a lot of promise. I'm not too sure about #ethereum",
  "Lang":"en",
  "Retweet":true,
  "Source":"",
  "User": {
    "Id":"14377871",
    "Name":"MagicalPipelines",
    "Description":"",
    "ScreenName":"Mitch",
    "URL":"http://blog.mitchseymour.com/",
    "FollowersCount":"120",
    "FriendsCount":"120"
  }
}
```