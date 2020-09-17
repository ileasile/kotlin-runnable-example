package ru.emkn.kotlin

fun slowFib(n: Int): Long {
    return when {
        n < 0 -> throw IllegalArgumentException("n should be non-negative")
        n <= 1 -> 1
        else -> slowFib(n - 1) + slowFib(n - 2)
    }
}

fun iterativeFib(n: Int): Long {
    if (n < 0) throw IllegalArgumentException("n should be non-negative")
    var a = 1L
    var res = 1L
    for (i in 2..n) {
        val t = res
        res += a
        a = t
    }
    return res
}
