import react.RBuilder
import react.dom.*
import react.router.dom.*

fun RBuilder.app() =
        div {
            headerComponent()
            mainComponent()
        }

fun RBuilder.headerComponent() =
        header{
            nav{
                ul {
                    li { navLink("/") { +"Home" }}
                    li { navLink("/roster") { +"Roster" }}
                    li { navLink("/schedule") { +"Schedule" }}
                }
            }
        }

fun RBuilder.mainComponent() =
        div {
            switch {
                route(exact = true, path = "/", component = Home::class)
                route(path = "/roster", component = Roster::class)
                route(path = "/schedule", component = Schedule::class)
            }
        }
