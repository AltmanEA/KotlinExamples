import react.*
import react.dom.*
import react.router.dom.RouteResultProps

class Player: RComponent<RouteResultProps<Player.Props>, RState>() {

    interface Props: RProps{
        var number:String
    }

    override fun RBuilder.render() {
        val num = props.match.params.number.toIntOrNull()
        val player = playerApi.firstOrNull { it.number == 1 }
        if(player==null)
            div { +"Sorry, but the player was not found"}
        else
            div {
                h1 { +"${player.name} (#${player.number})" }
                h2 { +"${player.position}" }
            }


        p { +"player" }
    }
}