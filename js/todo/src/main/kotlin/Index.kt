package reactredux

import components.app
import react.dom.render
import react.redux.provider
import reducers.State
import reducers.combinedReducers
import redux.*
import kotlin.browser.document


@JsModule("redux-logger")
external val middleware: dynamic
val logger =
    middleware.logger as Middleware<State, Action, Action, Action, Action>

val store = createStore<State, RAction, WrapperAction>(
    combinedReducers(), State(), compose(rEnhancer(), applyMiddleware(logger))
)

fun main() {
    val rootDiv = document.getElementById("root")
    render(rootDiv) {
        provider(store) {
            app()
        }
    }
}