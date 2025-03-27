package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
public class AppTest {
    static ByteArrayOutputStream output;

    @TempDir
    Path tmpDir;

    private String tmpDir() {
        return tmpDir.resolve("").toString();
    }

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
    @DisplayName("App shows error if path does not exist")
    public void appShowsErrorIfPathDoesNotExist() {
        app("abcd", "1");
        assertEquals("The path abcd doesn't exist.", getStdOut());
    }

    @Test
    @DisplayName("App can run task 1")
    public void task1IsRunnable() {
        app(tmpDir(), "1");
        assertEquals("TODO: Task 1\n", getStdOut());
    }

    @Test
    @DisplayName("App can run task 2")
    public void task2IsRunnable() {
        app(tmpDir(), "2");
        assertEquals("TODO: Task 2\n", getStdOut());
    }

    @Test
    @DisplayName("App can run task 3")
    public void task3IsRunnable() {
        app(tmpDir(), "3");
        assertEquals("TODO: Task 3\n", getStdOut());
    }

    @Test
    @DisplayName("App can run task 4")
    public void task4IsRunnable() {
        app(tmpDir(), "4");
        assertEquals("TODO: Task 4\n", getStdOut());
    }
}
