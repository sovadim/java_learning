package com.epam.ld.module2.testing.exceptions;

public class TemplateValueNotFoundError extends RuntimeException {
    public TemplateValueNotFoundError(String key) {
        super("Template value for key '" + key + "' not found");
    }
}
