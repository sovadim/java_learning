package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
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
}
