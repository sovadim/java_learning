package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class App {
    private static Messenger messenger;
    private final static Client client = new Client();

    private final static String helpString =
    """
    Usage:

    - Run in console mode:
        $ gradle run

    - Run in file mode:
        $ gradle run --args="<input file> <key-value pairs file> <output file>"

    In console mode, enter your template, the key-value pairs, comma-separated.
    Template can only be one-line in this mode.
    Example:
        - Template: "Hello, #{name} #{surname}!"
        - Key-value pairs: "name=John,surname=Doe"

    In file mode,
        the input file contains the template,
        the key-value pairs file contains the key-value pairs,
        generated text will be written to the output file.
    """;

    public static void main(String[] args) {
        switch (args.length) {
            case 0:
                runInConsoleMode();
                return;
            case 3:
                runInFileMode(args[0], args[1], args[2]);
                break;
            default:
                showHelp();
        }
    }

    private static void showHelp() {
        System.out.println(helpString);
    }

    private static void runInConsoleMode() {
        final var reader = new BufferedReader(new InputStreamReader(System.in));
        String template;
        String kvs;
        try {
            template = reader.readLine();
            kvs = reader.readLine();
            initConsoleMessenger();
            run(template, kvs);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void initConsoleMessenger() {
        messenger = new Messenger(
                new MailServer(new PrintWriter(System.out, true)),
                new TemplateEngine()
        );
    }

    private static void runInFileMode(final String inputFile,
                                      final String keyValuesFile,
                                      final String outputFile) {
        throw new RuntimeException("File mode, not implemented yet.");
    }

    private static void run(final String template,
                            final String keyValues) {
        parseKVIntoClient(keyValues);
        try {
            messenger.sendMessage(client, new Template(template));
        } catch (TemplateValueNotFoundError e) {
            System.out.println(e.getMessage());
        }
    }

    private static void parseKVIntoClient(final String input) {
        Arrays.stream(input.split(","))
                .map(s -> s.split("="))
                .forEach(p -> client.addTemplateValue(p[0], p[1]));
    }
}
