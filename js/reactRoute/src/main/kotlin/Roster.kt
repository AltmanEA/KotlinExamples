import react.*
import react.dom.h2
import react.router.dom.*

class Roster : RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        h2 { +"This is a roster page!" }
        switch {
            route("/roster", FullRoster::class, true)
            route("/roster/:number", Player::class)
        }
    }
}