package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

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
            if (origin.charAt(i) == '#') {
                ++i;
                if (i < origin.length() && origin.charAt(i) == '{') {
                    ++i;
                    while (i < origin.length() && origin.charAt(i) != '}') {
                        keyBuffer.append(origin.charAt(i));
                        ++i;
                    }
                    if (i < origin.length()) {
                        // Append value and clean key buffer
                        final var value = client.getValue(keyBuffer.toString());
                        if (value == null) {
                            throw new RuntimeException("No value for key: " + keyBuffer);
                        }
                        msgBuffer.append(value);
                        keyBuffer.setLength(0);
                    } else {
                        msgBuffer.append('#').append('{').append(keyBuffer);
                    }
                } else {
                    msgBuffer.append('#').append(origin.charAt(i));
                }
            } else {
                msgBuffer.append(origin.charAt(i));
            }
        }

        return msgBuffer.toString();
    }
}
