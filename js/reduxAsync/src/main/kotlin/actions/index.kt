package actions

import Item
import RThunk
import State
import fetch
import nullAction
import redditParse
import redux.RAction
import redux.WrapperAction
import kotlin.js.Date
import kotlin.reflect.KFunction0

class SelectSubreddit(val subreddit: String) : RAction

interface SubredditAction : RAction { val subreddit: String }

class InvalidateSubreddit(override val subreddit: String) : SubredditAction

class RequestPost(override val subreddit: String) : SubredditAction

class ReceivePosts(override val subreddit: String, val posts: Array<Item>) : SubredditAction {
    val receivedAt = Date.now()
}

fun fetchPosts(subreddit: String): RThunk = object : RThunk{
    override fun invoke(dispatch: (RAction) -> WrapperAction,
                        getState: KFunction0<*>): WrapperAction {
        dispatch(RequestPost(subreddit))
        fetch("https://www.reddit.com/r/${subreddit}.json")
            .then { it.text() }
            .then {
                dispatch(ReceivePosts(
                    subreddit,
                    redditParse(it).data.children
                        .mapIndexed { index, subreddit ->
                            Item(index, subreddit.data.title)
                        }.toTypedArray()
                ))
            }
        return nullAction
    }
}

fun shouldFetchPosts(state: State, subreddit: String): Boolean {
    val posts =
        state.postsBySubreddit[subreddit] ?: return true
    if(posts.isFetching) return false
    return posts.didInvalidate
}

fun fetchPostsIfNeeded(subreddit: String): RThunk = object : RThunk {
    override fun invoke(dispatch: (RAction) -> WrapperAction,
                        getState: KFunction0<*>): WrapperAction =
        if (shouldFetchPosts(getState().unsafeCast<State>(), subreddit))
            dispatch(fetchPosts(subreddit))
        else
            nullAction
}

