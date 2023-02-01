import matrix.FloatMatrix
import ml.ActivationFunction
import ml.DenseLayer
import kotlin.random.Random

fun randomInit(i: Int, j: Int) : Float {
    return Random.nextFloat()
}

fun main() {
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

    val output = denseLayer2.apply(denseLayer1.apply(input))
}