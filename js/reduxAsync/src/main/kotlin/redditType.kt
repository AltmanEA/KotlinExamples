import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

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

val json = Json(JsonConfiguration(strictMode = false))
fun redditParse(j: String) = json.parse(Reddit.serializer(), j)