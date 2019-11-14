package components

import containers.addTodo
import containers.visibleTodoList
import react.RBuilder
import react.dom.br
import react.dom.div
import react.dom.h1
import react.router.dom.browserRouter
import react.router.dom.navLink
import react.router.dom.route
import react.router.dom.switch

private const val HOME_PATH = "/"
private const val TODO_LIST_PATH = "/todolist"

fun RBuilder.app() =
    div {
        addTodo {}
        visibleTodoList {}
        footer()
    }


