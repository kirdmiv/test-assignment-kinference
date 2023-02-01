package matrix

interface Matrix {
    fun dot(other: Matrix): Matrix
    operator fun plus(other: Matrix): Matrix
    operator fun get(row: Int, column: Int): Number
}