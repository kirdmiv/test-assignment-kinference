package ml

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class ActivationFunctionTest {

    @Test
    fun sigmoid() {
        assertEquals(ActivationFunction.sigmoid(0f), 0.5f, 1e-4f)
        assertEquals(ActivationFunction.sigmoid(10000f), 1f, 1e-4f)
        assertEquals(ActivationFunction.sigmoid(-10000f), 0f, 1e-4f)
    }

    @Test
    fun relu() {
        assertEquals(ActivationFunction.relu(-10f), 0f, 1e-4f)
        assertEquals(ActivationFunction.relu(20f), 20f, 1e-4f)
    }
}