package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
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

    @ParameterizedTest(name = "Algorithm {0} moves file")
    @ValueSource(strings = {"1", "2", "3", "4"})
    @DisplayName("All algorithms can move file")
    public void allAlgorithmsCanMoveFile(String algorithmCode) throws Exception {
        addFiles("old");
        oldPath = tmpDir.resolve("old");
        newPath = tmpDir.resolve("new");

        app(oldPath.toString(), newPath.toString(), algorithmCode);

        assertTrue(Files.exists(newPath));
        assertTrue(Files.notExists(oldPath));
    }
}
