import kotlin.js.Promise

@JsModule("cross-fetch")
@JsNonModule
external fun fetch(url: String,
    options: dynamic= definedExternally): Promise<HTTPResult>

interface HTTPResult{
    fun json(): Promise<dynamic>
    fun text(): Promise<String>
}
