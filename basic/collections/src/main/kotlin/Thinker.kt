import java.util.*
import kotlin.collections.ArrayList

data class Candidates(val path: Path, val wordList: WordList)
data class PossibleWord(
    val index: Int,
    val char: Char,
    val path: Path,
    val word: String
)

class Thinker(private val board: Board, private val dict: Dict) {

    fun checkCandidate(path: Path, string: String): Path? =
        if (path.size == string.length)
            path
        else {
            val substring = board.pathToString(path)
            val positionSubs = string.indexOf(substring)
            if (positionSubs > 0)
                checkCandidateStep(
                    path, string, path.first(),
                    string[positionSubs - 1], 0
                )
            else
                checkCandidateStep(
                    path, string, path.last(),
                    string[path.size], path.size
                )
        }

    private fun checkCandidateStep(
        path: Path,
        string: String,
        index: Int,
        char: Char,
        newIndex: Int
    ): Path? {
        val iterator = board
            .getCharNeighbors(index)
            .filter { it !in path }
            .iterator()
        var result: Path? = null
        while (result.isNullOrEmpty() and iterator.hasNext()) {
            val addedIndex = iterator.next()
            if (board.data[addedIndex] == char)
                result = checkCandidate(
                    path.toMutableList().apply {
                        add(newIndex, addedIndex)
                    },
                    string
                )
        }
        return result
    }

    fun getFours(index: Int): Map<Path, String> {
        val forward =
            board.getFoursWithChars(index) as MutableMap
        val backward = forward
            .map { it.key.reversed() to it.value.reversed() }
            .toMap()
        forward.putAll(backward)
        return forward.toMap()
    }

    fun candidates(index: Int) =
        ArrayList<Candidates>().apply {
            getFours(index)
                .map { fours ->
                    dict.hash[fours.value]?.let {
                        add(Candidates(fours.key, it))
                    }
                }
        }

    fun wordsForChar(index: Int, char: Char): List<PossibleWord> {
        board.data[index] = char
        val result = candidates(index)
            .flatMap { (path, wordList) ->
                wordList.mapNotNull {word ->
                    checkCandidate(path, word)?.let {
                        PossibleWord(index, char, it, word)
                    }
                }
            }
        board.data[index] = ' '
        return result
    }

    fun getWordsForIndex(index: Int) =
        ArrayList<PossibleWord>().also { result ->
            ('a'..'z').map { char ->
                wordsForChar(index, char).forEach {
                    result.add(it)
                }
            }
        }


    fun getPossibleWord() =
        ArrayList<PossibleWord>().apply {
            addAll(
                board.getFreeIndexes()
                .flatMap {
                    getWordsForIndex(it)
                }
            )
            sortByDescending { it.word.length }
        }

    val x = TreeMap<Int, Int>()
}