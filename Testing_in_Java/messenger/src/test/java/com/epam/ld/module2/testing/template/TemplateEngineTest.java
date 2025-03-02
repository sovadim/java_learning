package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
