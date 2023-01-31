package matrix

import ml.ActivationFunction.relu
import ml.ActivationFunction.sigmoid
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class FloatMatrixTest {

    private fun A() : FloatMatrix {
        val data = floatArrayOf(0f, 1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f, 10f, 11f)
        return FloatMatrix(4, 3, data)
    }

    private fun B() : FloatMatrix {
        val data = floatArrayOf(11f, 5f, 4f, 7f, 18f, 0f, -3f, -10f, 23f, 3f, 4f, 5f)
        return FloatMatrix(4, 3, data)
    }

    private fun C() : FloatMatrix {
        val data = floatArrayOf(7f, 3f, 5f, -10f, -1f, 0f)
        return FloatMatrix(3, 2, data)
    }

    private fun `A plus B`() : FloatMatrix {
        val data = floatArrayOf(11f, 6f, 6f, 10f, 22f, 5f, 3f, -3f, 31f, 12f, 14f, 16f)
        return FloatMatrix(4, 3, data)
    }

    private fun `A dot B`() : FloatMatrix {
        val data = floatArrayOf(3f, -10f, 36f, -31f, 69f, -52f, 102f, -73f)
        return FloatMatrix(4, 2, data)
    }

    private fun `C apply relu`() : FloatMatrix {
        val data = floatArrayOf(7f, 3f, 5f, 0f, 0f, 0f)
        return FloatMatrix(3, 2, data)
    }

    private fun `C apply sigmoid`() : FloatMatrix {
        val data = floatArrayOf(0.99909f, 0.952574f, 0.993307f, 0f, 0.268941f, 0.5f)
        return FloatMatrix(3, 2, data)
    }

    @Test
    fun dot() {
        assertEquals(A().dot(C()), `A dot B`())
    }

    @Test
    fun plus() {
        assertEquals(A().plus(B()), `A plus B`())
    }

    @Test
    fun apply() {
        assertEquals(C().apply(::relu), `C apply relu`())
        assertEquals(C().apply(::sigmoid), `C apply sigmoid`())
    }

    @Test
    fun get() {
        assertEquals(C()[0, 1], 3f, 1e-4f)
        assertEquals(A()[2, 0], 6f, 1e-4f)
        assertEquals(B()[3, 2], 5f, 1e-4f)
    }

    @Test
    fun set() {
    }

    @Test
    fun constructor() {
        assertFails { FloatMatrix(10, 2, floatArrayOf(0f, 4f)) }
    }
}