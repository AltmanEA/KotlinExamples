package components

import Item
import react.RBuilder
import react.dom.key
import react.dom.li
import react.dom.ul

fun RBuilder.posts(posts: Array<Item>){
    ul {
        posts.mapIndexed {index, item ->
            li {
                attrs.key = index.toString()
                +item.title
            }
        }
    }
}