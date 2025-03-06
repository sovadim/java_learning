package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("UnitTests")
public class MailServerTest {
    MailServer server;

    @BeforeEach
    public void setUp() {
        server = new MailServer();
    }

    @Test
    @DisplayName("Server can send test")
    public void serverCanSendTest() {
        // Stub test, because server does nothing
        server.send("test", "test");
    }
}
