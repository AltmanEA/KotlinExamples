
import react.dom.*
import react.router.dom.browserRouter
import kotlin.browser.*

fun main(args: Array<String>) {
    render(document.getElementById("root")) {
        browserRouter {
            app()
        }
    }
}

enum class Position {
    G, D, M, F
}

class PlayerData(
        val number: Int,
        val name: String,
        val position: Position)

val playerApi = ArrayList<PlayerData>().apply{
    add(PlayerData(1, "Ben Blocker", Position.G))
    add(PlayerData(2, "BDave Defender", Position.D))
    add(PlayerData(3, "Sam Sweeper", Position.D))
    add(PlayerData(4, "Matt Midfielder", Position.M))
    add(PlayerData(5, "William Winger", Position.M))
    add(PlayerData(6, "Fillipe Forward", Position.F))
}