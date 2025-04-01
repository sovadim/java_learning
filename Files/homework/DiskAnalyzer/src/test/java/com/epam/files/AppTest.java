package com.epam.files;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
public class AppTest extends BaseTest {
    static ByteArrayOutputStream output;

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
    @DisplayName("Task 1 finds file with max 's'")
    public void task1FindsFileWithMaxS() throws Exception {
        addFiles("s1", "b2", "c3");
        app(tmpDir(), "1");
        assertEquals("s1\n", getStdOut());
    }

    @Test
    @DisplayName("Task 1 messages if no file found")
    public void task1MessagesIfNoFileFound() {
        app(tmpDir(), "1");
        assertEquals("No file with letter 's' found.\n", getStdOut());
    }

    @Test
    @DisplayName("Task 2 finds top 5 largest files")
    public void task2FindsTop5LargestFiles() throws Exception {
        addFileWithLoad("a", 1);
        addFileWithLoad("b", 2);
        app(tmpDir(), "2");
        assertEquals("b\na\n", getStdOut());
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
