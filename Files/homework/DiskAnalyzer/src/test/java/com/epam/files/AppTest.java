package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
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
    @DisplayName("App shows help if number of args is incorrect")
    public void appShowsHelpIfNumberOfArgsIsIncorrect() {
        app();
        assertTrue(getStdOut().startsWith("Usage:"));
    }

    @Test
    @DisplayName("App can run task 1")
    public void task1IsRunnable() {
        app("", "1");
        assertTrue(getStdOut().contains("TODO: Task 1"));
    }

    @Test
    @DisplayName("App can run task 2")
    public void task2IsRunnable() {
        app("", "2");
        assertTrue(getStdOut().contains("TODO: Task 2"));
    }

    @Test
    @DisplayName("App can run task 3")
    public void task3IsRunnable() {
        app("", "3");
        assertTrue(getStdOut().contains("TODO: Task 3"));
    }

    @Test
    @DisplayName("App can run task 4")
    public void task4IsRunnable() {
        app("", "4");
        assertTrue(getStdOut().contains("TODO: Task 4"));
    }
}
