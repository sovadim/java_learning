package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
public class AppTest extends BaseTest {
    static ByteArrayOutputStream output;

    public Path outputFile;

    private String tmpDir() {
        return tmpDir.resolve("").toString();
    }

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        outputFile = tmpDir.resolve("out.txt");
    }

    public void assertOutputEquals(String expected) throws Exception {
        String actual = getOutput();
        assertEquals(expected, actual);
    }

    private String getOutput() throws Exception {
        byte[] bytes = Files.readAllBytes(outputFile);
        return new String(bytes, StandardCharsets.UTF_8);
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
        assertTrue(getStdOut().contains("Usage:"));
    }

    @Test
    @DisplayName("App shows error if path does not exist")
    public void appShowsErrorIfPathDoesNotExist() throws Exception {
        app("abcd", "1", outputFile.toString());
        assertTrue(getStdOut().contains("The path abcd doesn't exist."));
    }

    @Test
    @DisplayName("Task 1 finds file with max 's'")
    public void task1FindsFileWithMaxS() throws Exception {
        addFiles("s1", "b2", "c3");
        app(tmpDir(), "1", outputFile.toString());
        assertOutputEquals("s1");
    }

    @Test
    @DisplayName("Task 1 messages if no file found")
    public void task1MessagesIfNoFileFound() throws Exception {
        app(tmpDir(), "1", outputFile.toString());
        assertOutputEquals("No file with letter 's' found.");
    }

    @Test
    @DisplayName("Task 2 finds top 5 largest files")
    public void task2FindsTop5LargestFiles() throws Exception {
        addFileWithLoad("a", 1);
        addFileWithLoad("b", 2);
        app(tmpDir(), "2", outputFile.toString());
        assertOutputEquals("b\na");
    }

    @Test
    @DisplayName("Task 3 counts average size of files")
    public void task3CountsAverageSizeOfFiles() throws Exception {
        addFileWithLoad("a", 1);
        addFileWithLoad("b", 2);
        app(tmpDir(), "3", outputFile.toString());
        assertOutputEquals("Average file size: 1.5 bytes.");
    }

    @Test
    @DisplayName("Task 4 counts files and folders starting with A")
    public void task4CountsFilesAndFoldersStartingWithA() throws Exception {
        addFiles(
                "aa/bb/abc/add",
                "a.txt",
                "A.exe"
        );
        app(tmpDir(), "4", outputFile.toString());
        assertOutputEquals("3 files and 2 folders begin with the letter A");
    }
}
