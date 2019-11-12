package containers

import actions.ToggleTodo
import actions.VisibilityFilter
import components.TodoList
import components.TodoListProps
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import reducers.State
import reducers.Todo
import redux.WrapperAction

private fun getVisibleTodos(todos: Array<Todo>, filter: VisibilityFilter): Array<Todo> = when (filter) {
    VisibilityFilter.SHOW_ALL -> todos
    VisibilityFilter.SHOW_ACTIVE -> todos.filter { !it.completed }.toTypedArray()
    VisibilityFilter.SHOW_COMPLETED -> todos.filter { it.completed }.toTypedArray()
}

private interface TodoListStateProps : RProps {
    var todos: Array<Todo>
}

private interface TodoListDispatchProps : RProps {
    var toggleTodo: (Int) -> Unit
}

val visibleTodoList: RClass<RProps> =
    rConnect<State, ToggleTodo, WrapperAction, RProps, TodoListStateProps, TodoListDispatchProps, TodoListProps>(
        { state, _ ->
            todos = getVisibleTodos(state.todos, state.visibilityFilter)
        },
        { dispatch, _ ->
            toggleTodo = { dispatch(ToggleTodo(it)) }
        }
    )(TodoList::class.js.unsafeCast<RClass<TodoListProps>>())