package com.jmp.multithreading.factorial;

import com.jmp.multithreading.factorial.errors.FactorialError;
import com.jmp.multithreading.factorial.errors.NegativeNumberError;

import java.math.BigInteger;

public class Factorial {
    public static BigInteger fact(long number) throws FactorialError {
        if (number < 0) {
            throw new NegativeNumberError(number);
        }
        if (number == 0) {
            return BigInteger.ONE;
        }
        var res = BigInteger.ONE;
        for (long i = 2; i <= number; ++i) {
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
}
