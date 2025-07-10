package com.jmp.multithreading;

import com.jmp.multithreading.factorial.Factorial;
import com.jmp.multithreading.factorial.FactorialFJP;
import com.jmp.multithreading.factorial.errors.FactorialError;

public class App {
    private static final String helpString =
            """
            Finds the factorial of a number.
            
            Usage:
            $ gradle run --args="<number> <number of threads>"
            """;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(helpString);
            return;
        }
        try {
            long number = Long.parseLong(args[0]);
            int numThreads = Integer.parseInt(args[1]);
            if (numThreads == 1) {
                System.out.println(Factorial.fact(number));
            } else {
                System.out.println(FactorialFJP.fact(number, numThreads));
            }
        } catch (NumberFormatException e) {
            System.out.println("Can't parse argument as a number: " + e.getMessage());
        } catch (FactorialError e) {
            System.out.println(e.getMessage());
        }
    }
}
