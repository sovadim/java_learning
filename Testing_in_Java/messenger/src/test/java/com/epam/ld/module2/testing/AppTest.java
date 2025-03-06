package com.epam.ld.module2.testing;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("AppTests")
public class AppTest {
    static ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    private void mockUserInput(final String input) {
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));
    }

    private String getStdOut() {
        return output.toString();
    }

    @Test
    @DisplayName("App can work in console mode")
    @Disabled
    public void appCanWorkInConsoleMode() {
        mockUserInput(
                "Hello, #{name} #{surname}!\n" +
                "name=John,surname=Doe"
        );
        App.main(new String[]{});
        assertEquals("Hello, John Doe!", getStdOut());
    }

    @Test
    @DisplayName("App in console mode will write error if key is missing")
    @Disabled
    public void appInConsoleModeWillWriteErrorIfKeyIsMissing() {
        mockUserInput(
                "Hello, #{name} #{surname}!\n" +
                "name=John"
        );
        App.main(new String[]{});
        assertEquals("Template value for key 'surname' not found", getStdOut());
    }

    @Test
    @DisplayName("App prints help if arguments number is incorrect")
    @Disabled
    public void whenCalledWithIncorrectArgumentsNumberPrintsHelpTest() {
        App.main(new String[]{"arg1", "arg2"});
        assertTrue(getStdOut().startsWith("Usage:"));
    }
}
