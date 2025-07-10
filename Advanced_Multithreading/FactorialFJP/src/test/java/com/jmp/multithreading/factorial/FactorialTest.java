package com.jmp.multithreading.factorial;

import com.jmp.multithreading.factorial.errors.FactorialError;
import com.jmp.multithreading.factorial.errors.NegativeNumberError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTest {
    @Test
    @DisplayName("Can't find a factorial of a negative number")
    public void cannotFindFactorialOfNegativeNumber() {
        var e = assertThrows(
                NegativeNumberError.class,
                () -> Factorial.fact(-1)
        );
        assertEquals("Can't find factorial of a negative number: -1", e.getMessage());
    }

    @Test
    @DisplayName("Factorial of 0 is 1")
    public void factorialOfZeroIsOne() throws FactorialError {
        assertEquals(BigInteger.ONE, Factorial.fact(0));
    }

    @Test
    @DisplayName("Factorials of some numbers")
    public void factorialsOfSomeNumbers() throws FactorialError {
        assertEquals(BigInteger.valueOf(1), Factorial.fact(1));
        assertEquals(BigInteger.valueOf(2), Factorial.fact(2));
        assertEquals(BigInteger.valueOf(6), Factorial.fact(3));
        assertEquals(BigInteger.valueOf(24), Factorial.fact(4));
        assertEquals(BigInteger.valueOf(120), Factorial.fact(5));
    }
}
