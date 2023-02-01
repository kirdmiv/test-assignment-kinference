package matrix

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class FloatMatrixPerformanceTest {
    @Test
    fun dot() {
        val A = FloatMatrix(40, 512) { i, j -> i * 12f + j * -3f }
        val B = FloatMatrix(512, 512) { i, j -> i * 0.7f + j * -0.5f }
        val time = measureTimeMillis {
            repeat(1000) {
                A.dot(B)
            }
        }
        println("Simple matrix multiplication takes $time mills.")
    }

    @Test
    fun dotCoroutines() {
        val A = FloatMatrix(40, 512) { i, j -> i * 12f + j * -3f }
        val B = FloatMatrix(512, 512) { i, j -> i * 0.7f + j * -0.5f }
        val time = measureTimeMillis {
            runBlocking {
                repeat(1000) {
                    A.dotCoroutines(B)
                }
            }
        }
        println("Coroutine matrix multiplication takes $time mills.")
    }

    @Test
    fun dotThreads() {
        val A = FloatMatrix(40, 512) { i, j -> i * 12f + j * -3f }
        val B = FloatMatrix(512, 512) { i, j -> i * 0.7f + j * -0.5f }
        val time = measureTimeMillis {
            repeat(1000) {
                A.dotTreads(B, 2)
            }
        }
        println("Thread matrix multiplication takes $time mills.")
    }
}