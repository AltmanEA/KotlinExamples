package components

import kotlinx.html.js.onClickFunction
import kotlinx.html.onClick
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.button
import react.dom.p
import redux.Action

class Counter() : RComponent<Counter.Props, RState>() {

    interface Props : RProps{
        var value: Int
        var onIncrement: ()-> Action
        var onDecrement: ()-> Action
    }

    override fun RBuilder.render() {
        p {
            +"Clicked ${props.value} раз "
            button {
                +"+"
                attrs.onClickFunction = { props.onIncrement() }
            }
            +" "
            button {
                +"-"
                attrs.onClickFunction = { props.onDecrement() }
            }
        }
    }
}

