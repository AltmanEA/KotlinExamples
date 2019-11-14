import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.*

typealias Path = List<Int>

class Board(
    val row: Int = 5,
    val col: Int = 5,
    val isDiag: Boolean = false
) {
    private val range = 0 until col * row

    val data =
        ArrayList<Char>().apply {
            range.map { add(' ') }
        }

    val distance: (Int, Int) -> Int =
        if (isDiag)
            { from: Int, to: Int ->
                max(
                    abs(from / row - to / row),
                    abs(from % row - to % row)
                )
            }
        else
            { from: Int, to: Int ->
                abs(from / row - to / row) +
                        abs(from % row - to % row)
            }


    private val neighborsStraight =
        setOf(-1, -col, col, 1)
    private val neighborsDiag =
        setOf(-col-1, -col+1, col-1, col+1)

    private val neighbors =
        if (isDiag)
            neighborsStraight.union(neighborsDiag)
        else
            neighborsStraight

    fun getNeighbors(index: Int) =
//        range.filter { distance(it, index) == 1 }
        neighbors
            .map { it + index }
            .filter {
                (it in range) and (distance(it, index) == 1)
            }

    fun getFreeNeighbors(index: Int)=
        getNeighbors(index)
            .filter { data[it] == ' ' }

    fun getCharNeighbors(index: Int): Set<Int> =
        getNeighbors(index)
            .filterTo(TreeSet<Int>()) { data[it] != ' ' }

    fun pathToString(path: Path)=
        path.fold ("", {
                s,i-> s+data[i]
        })

    fun addNeighbors(source: Path) =
        ArrayList<Path>().apply {
            getCharNeighbors(source.last())
                .filter { it !in source }
                .map { add(source + it) }
        }

    fun ArrayList<Path>.addNeighbors() =
        ArrayList<Path>().also{result->
            this.map { path->
                result.addAll(
                    addNeighbors(path)
                )
            }
        }

    fun getFours(index: Int) =
        addNeighbors(listOf(index))
            .addNeighbors()
            .addNeighbors()

    fun getFoursWithChars(index: Int) =
        getFours(index).associateWith { pathToString(it) }

    fun getFreeIndexes() =
        data
            .withIndex()
            .filter { it.value == ' ' }
            .map { it.index }


}