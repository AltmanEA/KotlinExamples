import react.*
import react.dom.h1

class Home: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h1 { +"Welcome to the Tornadoes Website!" }
    }
}