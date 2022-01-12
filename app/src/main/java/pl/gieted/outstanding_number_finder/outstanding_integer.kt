package pl.gieted.outstanding_number_finder

fun findOutstandingInteger(iterable: Iterable<Int>): Int {
    fun Int.isEven() = this % 2 == 0
    fun Int.isOdd() = !isEven()

    var oddInteger: Int? = null
    var evenInteger: Int? = null
    for (integer in iterable) {
        when {
            integer.isEven() && evenInteger != null -> return oddInteger ?: iterable.find { it.isOdd() }!!
            integer.isEven() && evenInteger == null -> evenInteger = integer
            integer.isOdd() && oddInteger != null -> return evenInteger ?: iterable.find { it.isEven() }!!
            integer.isOdd() && oddInteger == null -> oddInteger = integer
        }
    }

    throw IllegalStateException()
}
