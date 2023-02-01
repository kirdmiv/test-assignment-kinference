package ml

import matrix.FloatMatrix

class DenseLayer(
    val kernel: FloatMatrix,
    val bias: FloatMatrix,
    val activation: (Float) -> Float
) {
    constructor(
        kernelRows: Int, kernelColumns: Int, kernelInit: (Int, Int) -> Float,
        biasRows: Int, biasColumns: Int, biasInit: (Int, Int) -> Float,
        activation: (Float) -> Float
    ) : this(
        FloatMatrix(kernelRows, kernelColumns, kernelInit),
        FloatMatrix(biasRows, biasColumns, biasInit),
        activation
    )

    fun apply(input: FloatMatrix) : FloatMatrix {
        return input.dot(kernel).plus(bias).apply(activation)
    }

    fun applyThreads(input: FloatMatrix) : FloatMatrix {
        return input.dotTreads(kernel).plus(bias).apply(activation)
    }
}