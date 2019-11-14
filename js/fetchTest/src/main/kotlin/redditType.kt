import kotlinx.serialization.Serializable

@Serializable
class Reddit(
    val data: RedditData
)

@Serializable
class RedditData(
    val children: Array<Subreddit>
)

@Serializable
class Subreddit(
    val data: SubredditData
)

@Serializable
class SubredditData(
    val title: String
)