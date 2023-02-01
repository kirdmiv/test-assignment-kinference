package ml

import matrix.FloatMatrix
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class DenseLayerPerformanceTest {
    private fun randomInit(i: Int, j: Int) : Float {
        return Random.nextFloat()
    }

    @Test
    fun `apply dense layer with simple multiplication`() {
        val input = FloatMatrix(4, 512, ::randomInit)
        val denseLayer1 = DenseLayer(
            512, 512, ::randomInit,
            4, 512, ::randomInit,
            ActivationFunction::sigmoid
        )
        val denseLayer2 = DenseLayer(
            512, 512, ::randomInit,
            4, 512, ::randomInit,
            ActivationFunction::relu
        )

        val time = measureTimeMillis {
            repeat(1000) {
                denseLayer2.apply(denseLayer1.apply(input))
            }
        }
        println("Dense layers with simple matrix multiplication take $time mills.")
    }

    @Test
    fun `apply dense layer with fast multiplication`() {
        val input = FloatMatrix(4, 512, ::randomInit)
        val denseLayer1 = DenseLayer(
            512, 512, ::randomInit,
            4, 512, ::randomInit,
            ActivationFunction::sigmoid
        )
        val denseLayer2 = DenseLayer(
            512, 512, ::randomInit,
            4, 512, ::randomInit,
            ActivationFunction::relu
        )

        val time = measureTimeMillis {
            repeat(1000) {
                denseLayer2.applyThreads(denseLayer1.applyThreads(input))
            }
        }
        println("Dense layers with fast matrix multiplication take $time mills.")
    }
}