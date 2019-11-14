package containers

import actions.SetVisibilityFilter
import actions.VisibilityFilter
import components.Link
import components.LinkProps
import react.RClass
import react.RProps
import react.invoke
import react.redux.rConnect
import reducers.State
import redux.WrapperAction

interface FilterLinkProps : RProps {
    var filter: VisibilityFilter
}

private interface LinkStateProps : RProps {
    var active: Boolean
}

private fun LinkStateProps.mapStateToProps(
    state: State,
    ownProps: FilterLinkProps
) {
    active = state.visibilityFilter == ownProps.filter
}

private interface LinkDispatchProps : RProps {
    var onClick: () -> Unit
}

private fun LinkDispatchProps.mapDispatchToProps(
    dispatch: (SetVisibilityFilter) -> WrapperAction,
    ownProps: FilterLinkProps
) {
    onClick = {
        dispatch(SetVisibilityFilter(ownProps.filter))
    }
}

val filterLink=
    rConnect<State, SetVisibilityFilter, WrapperAction, FilterLinkProps, LinkStateProps, LinkDispatchProps, LinkProps>(
        LinkStateProps::mapStateToProps,
        LinkDispatchProps::mapDispatchToProps
    )(Link::class.js.unsafeCast<RClass<LinkProps>>())
