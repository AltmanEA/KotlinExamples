package reducers

import Items
import PostsBySubreddit
import PostsBySubredditDefault
import State
import actions.*
import redux.RAction
import redux.Reducer
import kotlin.reflect.KProperty1


fun selectedSubreddit(state: String = "reactjs", action: RAction) =
    when (action) {
        is SelectSubreddit -> action.subreddit
        else -> state
    }

fun posts(state: Items = Items(), action: RAction) =
    when (action) {
        is InvalidateSubreddit -> state.copy(didInvalidate = true)
        is RequestPost -> state.copy(isFetching = true, didInvalidate = false)
        is ReceivePosts -> state.copy(
            isFetching = false, didInvalidate = false,
            items = action.posts, lastUpdated = action.receivedAt
        )
        else -> state
    }


fun postsBySubreddit(state: PostsBySubreddit = PostsBySubredditDefault(), action: RAction) =
    when (action) {
        is InvalidateSubreddit,
        is ReceivePosts,
        is RequestPost
        -> {
            val subredditName = (action as SubredditAction).subreddit
            val subredditValue = state.getOrPut(subredditName, {Items()})
            val newSubredditValue = posts(subredditValue, action)
            val newState = state.toMutableMap()
            newState[subredditName] = newSubredditValue
            newState
        }
        else -> state
    }

fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return redux.combineReducers(reducers.mapKeys { it.key.name })
}

fun rootReducer() = combineReducers(
    mapOf(
        State::selectedSubreddit to ::selectedSubreddit,
        State::postsBySubreddit to ::postsBySubreddit
    )
)