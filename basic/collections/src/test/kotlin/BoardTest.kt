import kotlin.test.*

internal class BoardTest{
    val board = Board().apply {
        "      abc abcde abc      "
            .mapIndexed { index, char ->
                data[index] = char
            }
    }

    @Test fun distanceTest(){
        assertEquals(1, board.distance(0,1))
        assertEquals(5, board.distance(4,5))
        assertEquals(5, board.distance(9,10))
        val board1 = Board(isDiag = true)
        assertEquals(1, board1.distance(0,1))
        assertEquals(4, board1.distance(4,5))
        assertEquals(4, board1.distance(9,10))
    }

    @Test fun getNeighborTest(){
        val n1 = Board().getNeighbors(0)
        assertEquals(2, n1.size)
        assert(1 in n1)
        assert(5 in n1)
        val n2 = Board(isDiag = true)
            .getNeighbors(9)
        assertEquals(5, n2.size)
        arrayOf(3, 4, 8, 13, 14).map {
            assert(it in n2)
        }
    }

    @Test fun pathToStringTest(){
        assertEquals("abcde",
            board.pathToString(listOf(10, 11, 12, 13, 14)))
    }

    @Test fun getFoursTest(){
        val p1 =
            board.getFours(5)
        assertEquals(8, p1.size)
        val p2 =
            board.getFoursWithChars(22)
        assertEquals(5, p2.size)
    }
}