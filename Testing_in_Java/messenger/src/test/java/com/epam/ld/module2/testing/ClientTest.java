package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ClientTest {
    Client client;

    @BeforeEach
    public void setUp() {
        client = new Client();
    }

    @Test
    @DisplayName("Client returns null value for non-existing key")
    public void clientReturnsNullValueForNonExistingKey() {
        assertNull(client.getValue("Non existing key"));
    }

    @Test
    @DisplayName("Client can set and give template value")
    @Disabled
    public void clientCanAddTemplateValue() {
        client.addTemplateValue("key", "value");
        assertEquals("value", client.getValue("key"));
    }
}
