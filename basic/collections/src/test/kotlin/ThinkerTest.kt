import kotlin.test.*

internal class ThinkerTest{
    val board = Board().apply {
        "       o  hello          "
            .mapIndexed { index, char ->
                data[index] = char
            }
    }

    val dict = DictImp()
    val thinker = Thinker(board, dict)

    @Test
    fun checkCandidateTest() {
        val p1 = thinker.checkCandidate(
            listOf(11, 12, 13), "hello"
        )
        assertEquals(5, p1?.size)
    }

    @Test fun candidatesTest(){
        val can1 =
            thinker.candidates(10)
        assert("archelogy" in can1[0].wordList)
        assert("chello" in can1[1].wordList)
        assert("bellehood" in can1[3].wordList)
    }

    @Test fun wordForCharTest(){
        val words1 =
            thinker.wordsForChar(5, 'c')
        assertEquals(1, words1.size)
        val words2 =
            thinker.wordsForChar(17, 'p')
        assertEquals(1, words2.size)
    }

    @Test fun getWordsForIndexTest(){
        val words1 =
            thinker.getWordsForIndex(6)
        assertEquals(31, words1.size)
    }

    @Test fun getPossibleWordTest(){
        val words =
            thinker.getPossibleWord()
        assertEquals(123, words.size)
        assertEquals(6, words.first().word.length)
    }

}