package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Tag("UnitTests")
public class MessengerTest {
    MailServer server;
    MailServer spyServer;
    TemplateEngine templateEngine;
    Messenger messenger;

    @BeforeEach
    public void setUp() {
        server = new MailServer(new PrintWriter(System.out, true));
        spyServer = spy(server);
        templateEngine = new TemplateEngine();
        messenger = new Messenger(spyServer, templateEngine);
    }

    @Test
    @DisplayName("Messenger sends processed message")
    public void messengerSendsProcessedMessagesTest() {
        // Setup template
        var template = new Template(
                """
                Dear #{name},
                You are invited to #{eventName} on #{eventDate}
                Location: #{location}
                Time: #{eventTime}
                """
        );

        // Setup key-value pairs
        var client = new Client();
        client.addTemplateValue("name", "John Doe");
        client.addTemplateValue("eventName", "Cinema Night");
        client.addTemplateValue("eventDate", "30th. Feb.");
        client.addTemplateValue("location", "online");
        client.addTemplateValue("eventTime", "18:00");

        // Setup addresses
        client.setAddresses("my.addr@mail.com");

        messenger.sendMessage(client, template);

        verify(spyServer).send(
                "my.addr@mail.com",
                """
                Dear John Doe,
                You are invited to Cinema Night on 30th. Feb.
                Location: online
                Time: 18:00
                """
        );
    }

    @Test
    @DisplayName("Missing template key causes error")
    public void missingTemplateKeyCausesErrorTest() {
        var template = new Template("Hello, #{name}! Haven't seen you in a while.");
        var client = new Client();

        var e = assertThrows(
                TemplateValueNotFoundError.class,
                () -> messenger.sendMessage(client, template)
        );
        assertEquals("Template value for key 'name' not found", e.getMessage());
    }
}
