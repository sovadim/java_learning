package com.epam.ld.module2.testing.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemplateEngineTest {
    TemplateEngine templateEngine;

    @BeforeEach
    public void setUp() {
         templateEngine = new TemplateEngine();
    }

    @Test
    @DisplayName("Empty template results in empty text")
    public void emptyTemplateResultsInEmptyTextTest() {
        assertTrue(templateEngine.generateMessage(new Template(""), null).isEmpty());
    }

    @Test
    @DisplayName("Template with no placeholders returns same text")
    public void templateWithNoPlaceholdersReturnsSameTextTest() {
        assertEquals("Some text", templateEngine.generateMessage(new Template("Some text"), null));
    }
}
