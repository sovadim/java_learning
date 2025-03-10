package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.exceptions.TemplateValueNotFoundError;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        if (!Files.exists(Paths.get(inputFile))) {
            System.out.println("File not found: " + inputFile);
            return;
        }
        String template;
        try {
            template = Files.readString(Paths.get(inputFile));
        } catch (IOException e) {
            System.out.println("Error while reading input file: " + e.getMessage());
            return;
        }

        if (!Files.exists(Paths.get(keyValuesFile))) {
            System.out.println("File not found: " + keyValuesFile);
            return;
        }
        String keyValues;
        try {
            keyValues = Files.readString(Paths.get(keyValuesFile));
        } catch (IOException e) {
            System.out.println("Error while reading key-values file: " + e.getMessage());
            return;
        }

        initFileMessenger(outputFile);
        run(template, keyValues);
    }

    private static void initFileMessenger(final String outputFile) {
        try {
            var writer = new PrintWriter(new FileWriter(outputFile, true), true);
            messenger = new Messenger(
                    new MailServer(writer),
                    new TemplateEngine()
            );
        } catch (IOException e) {
            System.out.println("Error while creating output file: " + e.getMessage());
        }
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
