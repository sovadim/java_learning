package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@Tag("UnitTests")
public class MailServerTest {
    MailServer server;
    PrintWriter mockWriter;

    @BeforeEach
    public void setUp() {
        mockWriter = mock(PrintWriter.class);
        server = new MailServer(mockWriter);
    }

    @Test
    @DisplayName("Server can send text into specified output stream")
    public void serverCanSendTextIntoSpecifiedOutputStreamTest() {
        // Stub test, because server does nothing
        server.send("any addresses", "My test content");
        verify(mockWriter).write("My test content");
    }
}
