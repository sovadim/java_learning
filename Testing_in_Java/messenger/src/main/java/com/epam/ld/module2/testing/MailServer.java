package com.epam.ld.module2.testing;

import java.io.PrintWriter;

/**
 * Mail server class.
 */
public class MailServer {
    private final PrintWriter writer;

    /**
     * Initializes a new MailServer with desired output channel.
     *
     * @param writer the output stream
     */
    public MailServer(final PrintWriter writer) {
        this.writer = writer;
    }

    /**
     * Send notification.
     *
     * @param addresses      the addresses
     * @param messageContent the message content
     */
    public void send(final String addresses,
                     final String messageContent) {
        writer.write(messageContent);
        writer.flush();
    }
}
