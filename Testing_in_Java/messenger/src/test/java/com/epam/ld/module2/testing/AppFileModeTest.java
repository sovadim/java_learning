package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
public class AppFileModeTest {
    static ByteArrayOutputStream output;

    @TempDir
    Path tempDir;

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    private String getStdOut() {
        return output.toString();
    }

    private String templatePath() throws Exception {
        return Paths.get(getClass().getResource("/template.txt").toURI()).toString();
    }

    private String kvPath() throws Exception {
        return Paths.get(getClass().getResource("/kv.txt").toURI()).toString();
    }

    private String outputPath() {
        return tempDir.resolve("output.txt").toString();
    }

    private String resultContent() throws Exception {
        return Files.readString(Paths.get(outputPath()));
    }

    @Test
    @DisplayName("App will write error if input file is not found")
    public void appWillWriteErrorIfInputFileIsNotFoundTest() {
        App.main(new String[]{"abc.txt", "def.txt", "ghi.txt"});
        assertTrue(getStdOut().startsWith("File not found: abc.txt"));
    }

    @Test
    @DisplayName("App will write error if key-value file is not found")
    public void appWillWriteErrorIfKeyValueFileIsNotFoundTest() throws Exception {
        App.main(new String[]{templatePath(), "def.txt", "ghi.txt"});
        assertTrue(getStdOut().startsWith("File not found: def.txt"));
    }

    @Test
    @DisplayName("App can work in file mode")
    public void appCanWorkInFileModeTest() throws Exception {
        App.main(new String[]{templatePath(), kvPath(), outputPath()});
        assertEquals(
                """
                Subject: Welcome to Our Service
                
                Dear John Smith,
                
                Welcome to TechSolutions! Thank you for choosing our Premium Suite.
                
                Your account has been successfully created with the username: jsmith2025. You now have access to all our premium features including Cloud Storage, Advanced Analytics, and Priority Support.
                
                Should you have any questions, please contact our support team at help@techsolutions.com or call us at 1-800-555-1234.
                
                Your subscription will be active until December 10 2025.
                
                We're excited to have you on board!
                
                Best regards,
                Sarah Johnson
                Customer Success Team
                TechSolutions
                """,
                resultContent()
        );
    }

    @Test
    @EnabledOnOs(OS.MAC)
    void testRunOnlyOnMacTest() {
        // Just a stub conditional test
        assertTrue(true);
    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void testRunOnlyOnWindowsTest() {
        // Just a stub conditional test
        assertTrue(true);
    }
}

