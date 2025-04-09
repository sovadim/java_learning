package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest extends BaseTest {
    static ByteArrayOutputStream output;

    public Path oldPath;
    public Path newPath;

    private void assertStdOutEquals(String expected) {
        assertEquals(expected, getStdOut());
    }

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    private void app(String... args) {
        App.main(args);
    }

    private String getStdOut() {
        return output.toString();
    }

    @Test
    @DisplayName("App shows help if number of args is incorrect")
    public void appShowsHelpIfNumberOfArgsIsIncorrect() {
        app();
        assertTrue(getStdOut().contains("Usage:"));
    }

    @Test
    @DisplayName("App shows error if file does not exist")
    public void appShowsErrorIfFileDoesNotExist() {
        app("abc", "def", "42");
        assertStdOutEquals("File 'abc' does not exist\n");
    }

    @Test
    @DisplayName("App shows error if new file path already exists")
    public void appShowsErrorIfNewFilePathAlreadyExists() throws Exception {
        addFiles("file1", "file2");
        oldPath = tmpDir.resolve("file1");
        newPath = tmpDir.resolve("file2");

        app(oldPath.toString(), newPath.toString(), "42");
        assertStdOutEquals("File '" + newPath + "' already exist\n");
    }

    @Test
    @DisplayName("App shows error if algorithm code is incorrect")
    public void appShowsErrorIfAlgorithmCodeIsNotCorrect() throws Exception {
        addFiles("file1");
        oldPath = tmpDir.resolve("file1");
        newPath = tmpDir.resolve("file2");

        app(oldPath.toString(), newPath.toString(), "42");
        assertStdOutEquals("Algorithm code must be 1, 2, 3 or 4\n");
    }
}
