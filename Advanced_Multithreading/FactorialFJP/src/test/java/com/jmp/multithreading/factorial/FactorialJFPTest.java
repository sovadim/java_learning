package com.jmp.multithreading.factorial;

import com.jmp.multithreading.factorial.errors.FactorialError;
import com.jmp.multithreading.factorial.errors.IncorrectThreadNumberError;
import com.jmp.multithreading.factorial.errors.NegativeNumberError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialJFPTest {
    @Test
    @DisplayName("Can't find a factorial of a negative number")
    public void cannotFindFactorialOfNegativeNumber() {
        var e = assertThrows(
                NegativeNumberError.class,
                () -> FactorialFJP.fact(-1, 1)
        );
        assertEquals("Can't find factorial of a negative number: -1", e.getMessage());
    }

    @Test
    @DisplayName("Can't run on incorrect number of threads")
    public void cannotRunOnIncorrectNumberOfThreads() {
        var e = assertThrows(
                IncorrectThreadNumberError.class,
                () -> FactorialFJP.fact(1, 0)
        );
        assertEquals("Can't use requested number of threads: 0", e.getMessage());
    }

    @Test
    @DisplayName("Factorial of 0 is 1")
    public void factorialOfZeroIsOne() throws FactorialError {
        assertEquals(BigInteger.ONE, FactorialFJP.fact(0, 1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 10, 100})
    @DisplayName("Factorials of some numbers")
    public void factorialsOfSomeNumbers(int numThreads) throws FactorialError {
        assertEquals(BigInteger.valueOf(1), FactorialFJP.fact(1, numThreads));
        assertEquals(BigInteger.valueOf(2), FactorialFJP.fact(2, numThreads));
        assertEquals(BigInteger.valueOf(6), FactorialFJP.fact(3, numThreads));
        assertEquals(BigInteger.valueOf(24), FactorialFJP.fact(4, numThreads));
        assertEquals(BigInteger.valueOf(120), FactorialFJP.fact(5, numThreads));
    }
}
