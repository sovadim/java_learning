package com.jmp.multithreading;

import com.jmp.multithreading.factorial.Factorial;
import com.jmp.multithreading.factorial.errors.FactorialError;

public class App {
    private static final String helpString =
            """
            Finds the factorial of a number.
            
            Usage:
            $ gradle run --args="<number>"
            """;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(helpString);
            return;
        }
        try {
            System.out.println(
                    Factorial.fact(Long.parseLong(args[0]))
            );
        } catch (NumberFormatException e) {
            System.out.println("Can't parse argument as a number '" + args[0] + "'");
        } catch (FactorialError e) {
            System.out.println(e.getMessage());
        }
    }
}
