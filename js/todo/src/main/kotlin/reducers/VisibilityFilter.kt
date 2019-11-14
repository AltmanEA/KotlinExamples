package reducers

import redux.RAction
import actions.*

fun visibilityFilter(
    state: VisibilityFilter = VisibilityFilter.SHOW_ALL,
    action: RAction
): VisibilityFilter = when (action) {
    is SetVisibilityFilter -> action.filter
    else -> state
}