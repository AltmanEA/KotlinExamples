import containers.app
import react.dom.render
import react.redux.provider
import reducers.rootReducer
import redux.*
import kotlin.browser.document

@JsModule("redux-logger")
external val reduxLogger: dynamic
val logger =
    reduxLogger.logger as Middleware<State, Action, Action, Action, Action>

val middlewares =
    compose(rThunk(), rEnhancer(), applyMiddleware(logger))

val store =
    createStore<State, RAction, WrapperAction>(
        rootReducer(),
        State(),
        middlewares
    )

fun main() {
    val rootDiv = document.getElementById("root")
    render(rootDiv) {
        provider(store) {
                app{}
        }
    }
}