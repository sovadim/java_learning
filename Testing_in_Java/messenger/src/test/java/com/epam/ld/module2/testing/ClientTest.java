package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@Tag("UnitTests")
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
    public void clientCanAddTemplateValue() {
        client.addTemplateValue("key", "value");
        assertEquals("value", client.getValue("key"));
    }

    @Test
    @DisplayName("Client can set and get addresses")
    public void testGetAddresses() {
        client.setAddresses("test");
        assertEquals("test", client.getAddresses());
    }
}
