 package matrix

 import kotlin.math.abs

 class FloatMatrix(
    val rows: Int,
    val columns: Int,
    private val data: FloatArray
) {
    init {
        require(this.data.size == this.rows * this.columns)
    }

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

        return FloatMatrix(this.rows, other.columns) { row, col ->
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
            this[row, col] + other[row, col]
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

    private fun floatEquals(a: Float, b: Float) : Boolean {
        return abs(a - b) < 1e-4
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FloatMatrix

        if (rows != other.rows) return false
        if (columns != other.columns) return false
        if (this.data.size != other.data.size) return false
        for (i in this.data.indices) {
            if (!floatEquals(this.data[i], other.data[i])) return false
        }

        return true
    }

    override fun hashCode(): Int {
        var result = rows
        result = 31 * result + columns
        result = 31 * result + data.contentHashCode()
        return result
    }
}