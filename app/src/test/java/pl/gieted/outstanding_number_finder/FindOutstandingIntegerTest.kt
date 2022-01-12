package pl.gieted.outstanding_number_finder

import org.junit.Assert.assertEquals
import org.junit.Test

class FindOutstandingIntegerTest {

    @Test
    fun `finds outstanding number when it's odd`() {
        val list = listOf(11, 10, 6)
        val result = findOutstandingInteger(list)

        assertEquals(11, result)
    }

    @Test
    fun `finds outstanding number when it's even`() {
        val list = listOf(10, 11, 7)
        val result = findOutstandingInteger(list)

        assertEquals(10, result)
    }

    @Test
    fun `finds outstanding number when it's in the middle of the iterable`() {
        val list = listOf(1, 3, 150, 5, 7)
        val result = findOutstandingInteger(list)

        assertEquals(150, result)
    }

    @Test
    fun `finds outstanding number when it's at the end of the iterable`() {
        val list = listOf(6, 4, 2, 219)
        val result = findOutstandingInteger(list)

        assertEquals(219, result)
    }

    @Test
    fun `finds outstanding number when negative numbers are present`() {
        val list = listOf(10, 20, -20, -10, 100, -101)
        val result = findOutstandingInteger(list)

        assertEquals(-101, result)
    }
}
