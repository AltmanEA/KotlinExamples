import actions.Decrement
import actions.Increment
import actions.decrement
import actions.increment
import components.Counter
import react.dom.render
import reducers.counterReducer
import reducers.rCounterReducer
import redux.*
import kotlin.browser.document

data class State(val counter: Int = 0)

@JsModule("redux-logger")
external val middleware: dynamic
val logger = middleware.logger as Middleware<State, Action, Action, Action, Action>

val store = createStore(
    ::counterReducer,
    State(),
    applyMiddleware(logger)
)
val rStore = createStore<State, RAction, WrapperAction>(
    ::rCounterReducer,
    State(),
    compose(rEnhancer(), applyMiddleware(logger))
)

val rootDiv = document.getElementById("root")

fun render() =
    render(rootDiv) {
        child(Counter::class){
            attrs.value=store.getState().counter
            attrs.onIncrement = {
                store.dispatch(increment)
            }
            attrs.onDecrement = {
                store.dispatch(decrement)
            }
        }
    }

fun rRender() =
    render(rootDiv) {
        child(Counter::class){
            attrs.value=rStore.getState().counter
            attrs.onIncrement = {
                rStore.dispatch(Increment())
            }
            attrs.onDecrement = {
                rStore.dispatch(Decrement())
            }
        }
    }


//fun main() {
//    render()
//    store.subscribe { render() }
//}

fun main() {
    rRender()
    rStore.subscribe { rRender() }
}