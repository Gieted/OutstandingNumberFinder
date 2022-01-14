package pl.gieted.outstanding_number_finder

enum class IntegersParsingError {
    IncorrectFormat, LessThan3Numbers, InputBlank, MultipleOutstandingNumbers, NoOutstandingNumber
}

data class IntegersParsingResult(
    val integers: List<Int>? = null,
    val isValid: Boolean = integers != null,
    val error: IntegersParsingError? = null
)

fun parseIntegersInput(input: String): IntegersParsingResult {
    fun Int.isEven() = this % 2 == 0

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

    val evenIntegersCount = integers.count { it.isEven() }
    val oddIntegersCount = integers.size - evenIntegersCount

    if (evenIntegersCount == 0 || oddIntegersCount == 0) {
        return IntegersParsingResult(error = IntegersParsingError.NoOutstandingNumber)
    }

    if (evenIntegersCount > 1 && oddIntegersCount > 1) {
        return IntegersParsingResult(error = IntegersParsingError.MultipleOutstandingNumbers)
    }

    return IntegersParsingResult(integers)
}
