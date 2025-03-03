package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class TemplateEngineTest {
    TemplateEngine templateEngine;
    Client client;

    @BeforeEach
    public void setUp() {
         templateEngine = new TemplateEngine();
         client = new Client();
    }

    private String generateMessage(String template) {
        return templateEngine.generateMessage(new Template(template), client);
    }

    private void addValue(String key, String value) {
        client.addTemplateValue(key, value);
    }

    @Test
    @DisplayName("Empty template results in empty text")
    public void emptyTemplateResultsInEmptyTextTest() {
        assertTrue(generateMessage("").isEmpty());
    }

    @Test
    @DisplayName("Template with no placeholders returns same text")
    public void templateWithNoPlaceholdersReturnsSameTextTest() {
        assertEquals(
                "Some text",
                generateMessage("Some text")
        );
    }

    @Test
    @DisplayName("Value not present in template is ignored")
    public void valueNotPresentInTemplateIsIgnoredTest() {
        addValue("extra", "value");
        assertEquals(
                "sample text",
                generateMessage("sample text")
        );
    }

    @Test
    @DisplayName("Existing template value is substituted")
    public void existingTemplateValueIsSubstitutedTest() {
        addValue("name", "World");
        assertEquals(
                "Hello, World!",
                generateMessage("Hello, #{name}!")
        );
    }

    private static String getAllLatin1CharsWithoutCurlyBraces() {
        // Curly braces are filtered, because they are special symbols for parsing
        return IntStream.rangeClosed(0, 255)
                .filter(i -> i != '{' && i != '}')
                .mapToObj(i -> String.valueOf((char) i)) // Convert each int to a char, then to String
                .collect(Collectors.joining()); // Join all characters into a single string
    }

    @Test
    @DisplayName("Template key supports all Latin-1 characters")
    public void templateKeySupportsAllLatin1CharactersTest() {
        var latin1chars = getAllLatin1CharsWithoutCurlyBraces();

        addValue(latin1chars, "value");
        assertEquals(
                "abc value def",
                generateMessage("abc #{" + latin1chars + "} def")
        );
    }

    @Test
    @DisplayName("Template value supports all Latin-1 characters")
    public void templateValueSupportsAllLatin1CharactersTest() {
        var latin1chars = getAllLatin1CharsWithoutCurlyBraces();

        addValue("key", latin1chars);
        assertEquals(
                "abc " + latin1chars + " def",
                generateMessage("abc #{key} def")
        );
    }

    @Test
    @DisplayName("Non-existing template value causes error")
    public void nonExistingTemplateValueCausesErrorTest() {
        var e = assertThrows(
                TemplateValueNotFoundError.class,
                () -> generateMessage("Hello, #{name}!")
        );
        assertEquals("Template value for key 'name' not found", e.getMessage());
    }
}
