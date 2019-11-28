package containers

import Item
import actions.InvalidateSubreddit
import actions.SelectSubreddit
import actions.fetchPostsIfNeeded
import components.picker
import components.posts
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.*
import react.dom.button
import react.dom.div
import react.dom.h2
import react.dom.p
import redux.RAction
import kotlin.js.Date

interface AppProps : RProps {
    var selectedSubreddit: String
    var posts: Array<Item>
    var isFetching: Boolean
    var lastUpdate: Double?
    var dispatch: (RAction) -> Unit
}

class App : RComponent<AppProps, RState>() {

    override fun RBuilder.render() {
        div {
            picker(
                props.selectedSubreddit,
                ::handleChange,
                arrayOf("reactjs", "frontend")
            )
            p {
                props.lastUpdate?.let {
                    +"Last updated at ${Date(it).toLocaleString()}"
                }
                if (!props.isFetching) {
                    button {
                        attrs.onClickFunction = ::handleRefreshClick
                        +"Refresh"
                    }
                }
            }
            if (props.posts.isEmpty()) {
                h2 {
                    +if (props.isFetching) "Loading ... "
                    else "Empty."
                }
            } else {
                div {
                    posts(props.posts)
                }
            }
        }
    }

    override fun componentDidMount() =
        props.dispatch(fetchPostsIfNeeded(props.selectedSubreddit))


    override fun componentDidUpdate(prevProps: AppProps, prevState: RState, snapshot: Any) {
        if (prevProps.selectedSubreddit != props.selectedSubreddit)
            props.dispatch(fetchPostsIfNeeded(props.selectedSubreddit))
    }

    private fun handleChange(subreddit: String) {
        props.dispatch(SelectSubreddit(subreddit))
    }

    private fun handleRefreshClick(event: Event) {
        event.preventDefault()
        props.dispatch(InvalidateSubreddit(props.selectedSubreddit))
        props.dispatch(fetchPostsIfNeeded(props.selectedSubreddit))
    }

    override fun componentDidCatch(error: Throwable, info: RErrorInfo): Unit =
        console.log(error, info)
}
