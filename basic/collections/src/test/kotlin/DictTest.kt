import kotlin.test.*

internal class DictTest {

    val dict = DictImp()

    @Test
    fun foursTest() {
        val f1 = dict.fours("abcdef")
        assertEquals(3, f1.size)
        assertEquals("abcd", f1[0])
        assertEquals("cdef", f1.last())
        val f2 = dict.fours("abc")
        assertEquals(0, f2.size)
        val f3 = dict.fours("abcd")
        assertEquals(1, f3.size)
    }

    @Test
    fun addToHashTest() {
        dict.hash.clear()
        dict.addToHash("авто")
        assertEquals(1, dict.hash.size)
        assertEquals(1, dict.hash["авто"]?.size)
        dict.addToHash("автомобиль")
        assertEquals(7, dict.hash.size)
        assertEquals(2, dict.hash["авто"]?.size)
    }
}