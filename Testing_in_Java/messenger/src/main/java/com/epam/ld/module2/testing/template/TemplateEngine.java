package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(final Template template,
                                  final Client client) {
        final var origin = template.getTemplate();
        final var msgBuffer = new StringBuilder();
        final var keyBuffer = new StringBuilder();

        for (int i = 0; i < origin.length(); ++i) {
            // Check if the current position is the start of a placeholder
            if (origin.charAt(i) == '#'
                    && (i + 1) < origin.length()
                    && origin.charAt(i + 1) == '{') {
                // Skip 2 symbols of #{
                i += 2;
                // Read the key until the closing '}'
                while (i < origin.length() && origin.charAt(i) != '}') {
                    keyBuffer.append(origin.charAt(i));
                    ++i;
                }
                if (i < origin.length()) {
                    // Append value and clean key buffer
                    final var value = client.getValue(keyBuffer.toString());
                    if (value == null) {
                        throw new TemplateValueNotFoundError(keyBuffer.toString());
                    }
                    msgBuffer.append(value);
                    keyBuffer.setLength(0);
                } else {
                    // If key is read until the end, but no closing '}' was found
                    // then consider it was a part of text
                    msgBuffer.append("#{").append(keyBuffer);
                }
            } else {
                // It wasn't a placeholder, just append the character
                msgBuffer.append(origin.charAt(i));
            }
        }

        return msgBuffer.toString();
    }
}
