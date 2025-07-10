package com.jmp.multithreading.factorial.errors;

public class IncorrectThreadNumberError extends FactorialError {
    public IncorrectThreadNumberError(int numThreads) {
        super("Can't use requested number of threads: " + numThreads);
    }
}
