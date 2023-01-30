 package matrix

class FloatMatrix(
    val rows: Int,
    val columns: Int,
    private val data: FloatArray
) {
    constructor(rows: Int, columns: Int, init: (Int) -> Float) : this(
        rows,
        columns,
        FloatArray(rows * columns, init)
    )

    constructor(rows: Int, columns: Int, init: (Int, Int) -> Float) : this(
        rows,
        columns,
        FloatArray(rows * columns) { i -> init(i / columns, i % columns) }
    )

    fun dot(other: FloatMatrix): FloatMatrix {
        require(this.columns == other.rows)

        return FloatMatrix(rows, columns) { row, col ->
            var c = 0f
            for (k in 0 until this.columns) {
                c += this[row, k] * other[k, col]
            }
            c
        }
    }

    operator fun plus(other: FloatMatrix): FloatMatrix {
        require(this.rows == other.rows)
        require(this.columns == other.columns)

        return FloatMatrix(rows, columns) { row, col ->
            this[row, col] * other[row, col]
        }
    }

    fun apply(func: (Float) -> Float) : FloatMatrix{
        return FloatMatrix(this.rows, this.columns) { i -> func(this.data[i]) }
    }

    operator fun get(row: Int, column: Int): Float {
        return data[row * columns + column]
    }

    operator fun set(row: Int, column: Int, value: Float) {
        data[row * columns + column] = value
    }
}