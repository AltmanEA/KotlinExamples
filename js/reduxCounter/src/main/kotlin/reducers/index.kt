package reducers

import State
import actions.Decrement
import actions.Increment
import redux.Action
import redux.RAction

fun counterReducer(state: State = State(), action: Action) =
    when(action.type){
        "INCREMENT" -> State(state.counter+1)
        "DECREMENT" -> State(state.counter-1)
        else -> state
    }

fun rCounterReducer(state: State = State(), action: RAction) =
    when(action){
        is Increment -> State(state.counter+1)
        is Decrement-> State(state.counter-1)
        else -> state
    }