package com.jmp.multithreading.factorial;

import com.jmp.multithreading.factorial.errors.FactorialError;
import com.jmp.multithreading.factorial.errors.IncorrectThreadNumberError;
import com.jmp.multithreading.factorial.errors.NegativeNumberError;

import java.math.BigInteger;

public class FactorialFJP {
    public static BigInteger fact(long number, int numThreads) throws FactorialError {
        if (number < 0) {
            throw new NegativeNumberError(number);
        }
        if (numThreads < 1) {
            throw new IncorrectThreadNumberError(numThreads);
        }

        // TODO

        return BigInteger.ZERO;
    }
}
