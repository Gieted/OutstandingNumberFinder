package pl.gieted.outstanding_number_finder

import org.junit.Assert.*
import org.junit.Test

class ParseIntegersInputTest {
    @Test
    fun `should fail when input is blank`() {
        val result1 = parseIntegersInput("")
        assertFalse(result1.isValid)
        assertEquals(IntegersParsingError.InputBlank, result1.error)
        assertNull(result1.integers)

        val result2 = parseIntegersInput("   ")
        assertFalse(result2.isValid)
        assertEquals(IntegersParsingError.InputBlank, result1.error)
        assertNull(result2.integers)
    }

    @Test
    fun `should fail when less than 3 numbers are provided`() {
        val result = parseIntegersInput("1, 2")
        assertFalse(result.isValid)
        assertEquals(IntegersParsingError.LessThan3Numbers, result.error)
        assertNull(result.integers)
    }

    @Test
    fun `should fail when input is incorrect`() {
        val result1 = parseIntegersInput("1, 2,")
        assertFalse(result1.isValid)
        assertEquals(IntegersParsingError.IncorrectFormat, result1.error)
        assertNull(result1.integers)

        val result2 = parseIntegersInput("%23rfj")
        assertFalse(result2.isValid)
        assertEquals(IntegersParsingError.IncorrectFormat, result1.error)
        assertNull(result2.integers)
    }

    @Test
    fun `should correctly parse when input is valid`() {
        val result1 = parseIntegersInput("1, -2, 3")
        assertTrue(result1.isValid)
        assertNull(result1.error)
        assertEquals(listOf(1, -2, 3), result1.integers)

        val result2 = parseIntegersInput("1, -2, 3   ")
        assertTrue(result2.isValid)
        assertNull(result2.error)
        assertEquals(listOf(1, -2, 3), result2.integers)
    }
}
