package ml

import kotlin.math.exp

object ActivationFunction {
    fun sigmoid(x: Float): Float {
        return 1.0f / (1.0f + exp(-x))
    }

    fun relu(x: Float) : Float {
        return maxOf(0.0f, x)
    }
}