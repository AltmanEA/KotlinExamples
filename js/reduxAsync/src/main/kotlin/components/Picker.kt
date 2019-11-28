package components

import kotlinx.html.js.onChangeFunction
import kotlinx.html.onChange
import org.w3c.dom.events.Event
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.*

fun RBuilder.picker(
    value: String,
    onChange: (String)->Unit,
    options: Array<String>) =
    span {
        h1 { +value }
        select() {
            attrs.onChangeFunction =
                {event: dynamic ->
                    onChange(event.target.value)
                }
            options.map {
                option{
                    attrs.key = it
                    attrs.value = it
                    +it
                }
            }
        }
    }