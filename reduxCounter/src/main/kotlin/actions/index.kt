package actions

import redux.RAction

inline fun action(type: String, other: dynamic.() -> Unit = {}): dynamic {
    val obj = js("{}")
    obj["type"] = type
    other(obj)
    return obj
}

val increment = action("INCREMENT")
val decrement = action("DECREMENT")

class Increment : RAction
class Decrement : RAction