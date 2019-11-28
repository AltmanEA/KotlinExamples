import react.*
import react.dom.*

class Schedule: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            ul {
                li { +"6/5 @ Evergreens" }
                li { +"6/8 vs Kickers" }
                li { +"6/14 @ United"}
            }
        }
    }
}
