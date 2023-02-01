package matrix

interface Matrix {
    /***
     * Matrix multiplication.
     */
    fun dot(other: Matrix): Matrix

    /***
     * Add two matrices.
     */
    operator fun plus(other: Matrix): Matrix

    /***
     * Get value by row and column.
     */
    operator fun get(row: Int, column: Int): Number
}