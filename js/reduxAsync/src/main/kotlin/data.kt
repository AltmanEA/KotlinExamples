data class Item(
    val id: Int,
    val title: String
)

data class Items(
    val isFetching: Boolean = false,
    val didInvalidate: Boolean = false,
    val lastUpdated: Double? = null,
    val items: Array<Item>  = emptyArray()
)

data class State(
    val selectedSubreddit: String = "reactjs",
    val postsBySubreddit: PostsBySubreddit = PostsBySubredditDefault()
)

interface PostsBySubreddit : MutableMap<String, Items>
class PostsBySubredditDefault() : HashMap<String, Items>(), PostsBySubreddit