package containers

import State
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect

interface AppOwnProps : RProps

private fun AppProps.mapStateToProps(
    state: State,
    ownProps: RProps
) {
    selectedSubreddit = state.selectedSubreddit
    val post = state.postsBySubreddit[selectedSubreddit]
    if(post!=null){
        isFetching = post.isFetching
        lastUpdate = post.lastUpdated
        posts = post.items
    } else {
        isFetching = true
        posts = emptyArray()
    }
}

val app =
    rConnect<State, AppOwnProps, AppProps> (
        AppProps::mapStateToProps
    )(
        App::class.js.unsafeCast<RClass<AppProps>>()
    )




