package com.epam.ld.module2.testing;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Client.
 */
public class Client {
    private String addresses;

    private final Map<String, String> templateValues = new HashMap<>();

    /**
     * Gets addresses.
     *
     * @return the addresses
     */
    public String getAddresses() {
        return addresses;
    }

    /**
     * Sets addresses.
     *
     * @param addresses the addresses
     */
    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    /**
     * Gets value for template by key, if exists.
     *
     * @param key template key
     * @return value
     */
    public String getValue(final String key) {
        return templateValues.get(key);
    }

    /**
     * Add template value.
     *
     * @param key template key
     * @param value template value for key
     */
    public void addTemplateValue(final String key,
                                 final String value) {
        templateValues.put(key, value);
    }
}
