package com.jmp.multithreading.factorial.errors;

public class NegativeNumberError extends FactorialError {
    public NegativeNumberError(long number) {
        super("Can't find factorial of a negative number: " + number);
    }
}
