package com.jmp.multithreading;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest {
    static ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    private String getStdOut() {
        return output.toString();
    }

    private void app(String... args) {
        App.main(args);
    }

    @Test
    @DisplayName("Shows help if number of args is incorrect")
    public void showsHelpIfNumberOfArgsIsIncorrect() {
        app();
        assertTrue(getStdOut().startsWith("Finds"));
    }

    @Test
    @DisplayName("Shows error if number arg is incorrect")
    public void showsErrorIfNumberArgIsIncorrect() {
        app("abc");
        assertEquals("Can't parse argument as a number 'abc'\n", getStdOut());
    }

    @Test
    @DisplayName("Shows error if number is negative")
    public void showsErrorIfNumberIsNegative() {
        app("-1");
        assertEquals("Can't find factorial of a negative number: -1\n", getStdOut());
    }

    @Test
    @DisplayName("Returns the result if the args are correct")
    public void returnsResultIfArgsAreCorrect() {
        app("1");
        assertEquals("1\n", getStdOut());
    }
}
