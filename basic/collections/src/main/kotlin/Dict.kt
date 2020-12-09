typealias WordList = List<String>
typealias WordsHash = Map<String, WordList>

interface Dict {
    val hash: WordsHash
}

class DictImp : Dict {
    override val hash: MutableMap<String, MutableList<String>>
            = HashMap()

    val words = Dict::class
        .java.getResource("words.txt")
        .readText()
        .split("\r\n")
        .toTypedArray()
        .apply {
            forEach {
                addToHash(it)
            }
        }

    fun fours(word: String) =
        ArrayList<String>().also { list ->
            if (word.length > 3)
                (0..word.length - 4).map {
                    list.add(word.slice(it..it + 3))
                }
        }

    fun addToHash(word: String) =
        fours(word).map {
            hash.getOrPut(it, { ArrayList<String>() })
                .add(word)
        }

}