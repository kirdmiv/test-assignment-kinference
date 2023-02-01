# Test assignment
First, I want to state the task and goals I have tried to achieve.

### Task
Apply two different dense layers to an input matrix. Each layer has a kernel, a bias (which should be filled with random values by default) and an activation function. How to apply a dense layer? First, one should multiply the input matrix by the kernel matrix (using matrix multiplication). Then add a bias matrix. And finally, apply the activation function (for each matrix element).

### Goals
1. Main goal: Write clean and clear code.
2. Code should contain some classes to separate logic but not hundreds of classes with several layers of hierarchy.
3. Avoid complex language concepts if it is possible to write code without them.
4. Avoid complex algorithms, which are hard to implement and require a lot of testing, if they do not give a lot of performance boost on small matrixes (which we should use in this task).
5. It should be possible to use this code in the future. But, I assume that one who decides to use this code will have access to the source code and a right to modify it. Therefore, the main aim is the current task.

## Implementation walkthrough
### FloatMatrix class
I had several thoughts about how to implement this class. The main concern was data structure which will hold data in matrix cells.
It is possible to use a generic List, a generic Array or a primitive array.
I have decided to use a primitive implementation of arrays. This means that if one chooses to write a Matrix class with doubles or integers, they should duplicate the code, which is terrible but solves some problems with generics. Because using generics means that many functions should be inline, and there is no better way to write "times" or "plus" for all numeric types than this.

### DenseLayer class
The dense layer has a kernel and bias represented by FloatMatrix, activation function and method "apply". It is a simple class.

### Activation functions
I have decided to implement it like math libraries are often implemented. So, I have a singleton class (object) ActivationFunctions, which has "sigmoid" and "relu" static functions. I think it is better to have popular activation functions in this class rather than separate classes for all activation functions. If one wants to use a different activation function, they can pass it as a lambda or as a (Float) -> Float function straight to the dense layer.

## Tests
I have implemented a few tests for each used function. Plus, there is a test class to measure the performance of different matrix multiplication implementations. Of course, this test does not mean delivering 100% accurate results. But it shows twice improved performance of matrix multiplication.

## Matrix multiplication
There are several ways to improve performance:
1. Better asymptotics.
2. Parallel algorithms.

In the first case, there exists the Strassen algorithm. But, it is hard to implement and should give a little performance boost on small matrices given in the task due to its complex nature.

In the second case, there are (at least) three different ways to do it:
1. GPU computations

I am unfamiliar with them, and these computations could be platform specific (for example, CUDA API is unavailable on AMD GPUs). Therefore, I have decided to move forward in terms of this project. But, I believe multiplying matrices on GPU is the fastest way to do it.

2. Coroutines.

The performance test shows that I could not get any performance boost using coroutines. And it is an expected result because coroutines are great when a function needs to wait a lot (for a network connection, for example). Moreover, using `btop` utility, I found that only one CPU logical core is used. So, I might have written an inefficient code.

3. Threads.

I was able to boost dot() performance twice. Everyone is familiar with threads, so the code is clear to everyone (unlike coroutines).

## Possible future development
1. Create a matrix interface.
2. Write builder class for dense layer.
3. Write documentation.
4. Add other popular activation functions.
5. Provide alternatives to FloatMatrixes.
6. Add more methods to Matrix classes.