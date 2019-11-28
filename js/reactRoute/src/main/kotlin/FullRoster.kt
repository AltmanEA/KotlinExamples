import react.*
import react.dom.*
import react.router.dom.navLink

class FullRoster: RComponent<RProps, RState>() {
    override fun RBuilder.render() {
        div {
            ul {
                playerApi.map {
                    li {
                        attrs.key = it.number.toString()
                        navLink("/roster/${it.number}"){
                            +it.name
                        }
                    }
                }
            }
        }
    }
}