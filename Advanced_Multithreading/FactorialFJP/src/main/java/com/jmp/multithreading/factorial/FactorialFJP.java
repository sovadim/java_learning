package com.jmp.multithreading.factorial;

import com.jmp.multithreading.factorial.errors.FactorialError;
import com.jmp.multithreading.factorial.errors.IncorrectThreadNumberError;
import com.jmp.multithreading.factorial.errors.NegativeNumberError;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FactorialFJP {
    public static BigInteger fact(long number, int numThreads) throws FactorialError {
        if (number < 0) {
            throw new NegativeNumberError(number);
        }
        if (numThreads < 1) {
            throw new IncorrectThreadNumberError(numThreads);
        }
        if (number == 0) {
            return BigInteger.ONE;
        }
        try (var pool = new ForkJoinPool(numThreads)) {
            return pool.invoke(new FactorialTask(1, number));
        }
    }
}

class FactorialTask extends RecursiveTask<BigInteger> {
    private final long start;
    private final long end;

    public FactorialTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected BigInteger compute() {
        if (end - start == 0) {
            return BigInteger.valueOf(start);
        }
        long mid = (start + end) / 2;
        var left  = new FactorialTask(start, mid);
        var right = new FactorialTask(mid + 1, end);

        left.fork();
        BigInteger rightRes = right.compute();
        return rightRes.multiply(left.join());
    }
}
