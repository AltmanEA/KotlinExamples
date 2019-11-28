import redux.*
import kotlinext.js.js
import kotlin.reflect.KFunction0

interface RThunk : RAction {
    operator fun invoke(
        dispatch: (RAction) -> WrapperAction,
        getState: KFunction0<*>
    ): WrapperAction
}

fun <S> rThunk() =
    applyMiddleware<S, RAction, WrapperAction, RAction, WrapperAction>(
        {store ->
            {next ->
                {action ->
                    if(action is RThunk)
                        action(store::dispatch, store::getState)
                    else
                        next(action)
                }
            }
        }
    )


// This works too:
//fun <S> rThunk() : Enhancer<S, RAction, WrapperAction, RAction, WrapperAction> =
//    { next ->
//        { reducer, initialState ->
//            val oldStore = next(reducer, initialState)
//            val store = assign(js {}, oldStore)
//            store.dispatch = { action: RAction ->
//                if (action is RThunk) {
//                    action(store.dispatch, store.getState)
//                } else
//                    oldStore.dispatch(action)
//            }
//            store.unsafeCast<Store<S, RAction, WrapperAction>>()
//        }
//    }

val nullAction = js {}.unsafeCast<WrapperAction>()