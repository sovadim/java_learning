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

    public Path inputFile;
    public Path newDir;

    private String tmpDir() {
        return tmpDir.resolve("").toString();
    }

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
    @DisplayName("App shows error if directory does not exist")
    public void appShowsErrorIfDirDoesNotExist() throws Exception {
        addFiles("file");
        inputFile = tmpDir.resolve("file");

        app(inputFile.toString(), "def", "42");
        assertStdOutEquals("Directory 'def' does not exist\n");
    }

    @Test
    @DisplayName("App shows error if directory is not a directory")
    public void appShowsErrorIfDirIsNotDir() throws Exception {
        addFiles("file", "dir");
        inputFile = tmpDir.resolve("file");
        newDir = tmpDir.resolve("dir");

        app(inputFile.toString(), newDir.toString(), "42");
        assertStdOutEquals("Path '" + newDir + "' is not a directory\n");
    }

    @Test
    @DisplayName("App shows error if algorithm code is incorrect")
    public void appShowsErrorIfAlgorithmCodeIsNotCorrect() throws Exception {
        addFiles("file");
        addDirs("dir");
        inputFile = tmpDir.resolve("file");
        newDir = tmpDir.resolve("dir");

        app(inputFile.toString(), newDir.toString(), "42");
        assertStdOutEquals("Algorithm code must be 1, 2, 3 or 4\n");
    }
}
