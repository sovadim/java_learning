package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import org.junit.jupiter.api.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Tag("UnitTests")
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

    @Test
    @DisplayName("Text may consist on one placeholder")
    public void textCanBeOnlyAPlaceholderTest() {
        addValue("key", "value");
        assertEquals("value", generateMessage("#{key}"));
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
    @DisplayName("Template key and value may be empty strings")
    public void templateKeyAndValueMayBeEmptyStringsTest() {
        addValue("key", "");
        addValue("", "value");
        assertEquals(
                "value",
                generateMessage("#{key}#{}")
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

    @Test
    @DisplayName("Template key is case sensitive")
    public void templateKeyIsCaseSensitiveTest() {
        addValue("key", "value");
        var e = assertThrows(
                TemplateValueNotFoundError.class,
                () -> generateMessage("#{KEY}!")
        );
        assertEquals("Template value for key 'KEY' not found", e.getMessage());
    }

    @Test
    @DisplayName("Missing # is incorrect template syntax")
    public void missingHashIsIncorrectTemplateSyntaxTest() {
        assertEquals("Hello, {name}.", generateMessage("Hello, {name}."));
    }

    @Test
    @DisplayName("Missing { is incorrect template syntax")
    public void missingOpeningBraceIsIncorrectTemplateSyntaxTest() {
        assertEquals("Hello, #name}.", generateMessage("Hello, #name}."));
    }

    @Test
    @DisplayName("Missing } is incorrect template syntax")
    public void missingClosingBraceIsIncorrectTemplateSyntaxTest() {
        assertEquals("Hello, #{name.", generateMessage("Hello, #{name."));
    }

    @Test
    @DisplayName("Nested placeholder is incorrect template syntax")
    public void nestedPlaceholderIsIncorrectTemplateSyntaxTest() {
        addValue("34#{56", "-");
        assertEquals(
                "12-78}90",
                generateMessage("12#{34#{56}78}90")
        );
    }

    @Test
    @DisplayName("Extra # does not break parsing")
    public void extraHashSignDoesNotBreakParsingTest() {
        addValue("key", "value");
        assertEquals(
                "###value",
                generateMessage("####{key}")
        );
    }

    @Test
    @DisplayName("# in the end of template does not break parsing")
    public void hashSighInTheEndOfTemplateDoesNotBreakParsingTest() {
        assertEquals("###", generateMessage("###"));
    }

    @Test
    @DisplayName("Placeholder opening #{ in the end of template does not break parsing")
    public void placeholderOpeningInTheEndOfTemplateDoesNotBreakParsingTest() {
        assertEquals("Hello, #{", generateMessage("Hello, #{"));
    }

    @Test
    @DisplayName("Extra { is part of template")
    public void extraOpeningBraceIsPartOfTemplateTest() {
        addValue("{{{key", "value");
        assertEquals("value", generateMessage("#{{{{key}"));
    }

    @Test
    @DisplayName("Extra } is part of template")
    public void extraClosingBraceIsPartOfTextTest() {
        addValue("key", "value");
        assertEquals("value}}}", generateMessage("#{key}}}}"));
    }
}
