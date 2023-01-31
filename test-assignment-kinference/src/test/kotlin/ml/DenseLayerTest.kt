package ml

import matrix.FloatMatrix
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DenseLayerTest {
    private fun A() : FloatMatrix {
        val data = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f)
        return FloatMatrix(2, 4, data)
    }

    private fun B() : FloatMatrix {
        return FloatMatrix(4, 4) { i, j -> (i * 2 + j * 3).toFloat() }
    }

    @Test
    fun apply() {
        val denseLayer = DenseLayer(
            4, 4, { i, j -> (i * 2 + j * 3).toFloat() } ,
            2, 4, { _, _ -> 0f },
            { x -> x }
        )
        assertEquals(denseLayer.apply(A()), A().dot(B()))
    }
}