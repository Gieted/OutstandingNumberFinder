package pl.gieted.outstanding_number_finder

enum class IntegersParsingError {
    IncorrectFormat, LessThan3Numbers, InputBlank
}

data class IntegersParsingResult(
    val integers: List<Int>? = null,
    val isValid: Boolean = integers != null,
    val error: IntegersParsingError? = null
)

fun parseIntegersInput(input: String): IntegersParsingResult {
    if (input.isBlank()) {
        return IntegersParsingResult(error = IntegersParsingError.InputBlank)
    }

    val integers = input.split(",").map {
        try {
            it.trim().toInt()
        } catch (e: NumberFormatException) {
            return IntegersParsingResult(error = IntegersParsingError.IncorrectFormat)
        }
    }

    if (integers.size < 3) {
        return IntegersParsingResult(error = IntegersParsingError.LessThan3Numbers)
    }

    return IntegersParsingResult(integers)
}
