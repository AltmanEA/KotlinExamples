import kotlinx.css.html
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.dom.HTMLDivElement
import kotlin.browser.document

// Comment to run test
fun main() {
    val rootDiv =
        document.getElementById("root") as HTMLDivElement

    val json = Json(JsonConfiguration(strictMode = false))

    fetch("https://www.reddit.com/r/reactjs.json")
        .then {
            it.text()
        }.then {
            val reddit = json.parse(
                Reddit.serializer(), it
            )
            val child =
                reddit.data.children
                    .map { it.data.title }
                    .fold("") { acc, curr ->
                        "$acc<li>$curr</li>"
                    }
            rootDiv.innerHTML = "<ol>Subreddits:$child</ol>"
        }
}