package com.epam.files;

import com.epam.files.db.FileDB;

import java.nio.file.Path;
import java.util.Scanner;

public class App {
    private final static String helpString =
            """
            File Sharing App
            Usage:
            $ gradle run --args="<address> <db name> <user> <password>"
            
            REPL commands:
            * add <path>         - add a file from the path to the database
            * get <name> <path>  - retrieve a file from a database and write to the path
            * exit               - stop REPL loop
            """;

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(helpString);
            return;
        }
        final String address = args[0];
        final String name = args[1];
        final String user = args[2];
        final String pass = args[3];

        try (FileDB db = new FileDB(address, name, user, pass)) {
            var scanner = new Scanner(System.in);

            while (true) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");
                String command = parts[0];

                switch (command) {
                    case "add":
                        if (parts.length != 2) {
                            System.out.println("Usage: add <path from>");
                            continue;
                        }
                        String path = parts[1];
                        try {
                            db.add(Path.of(path).getFileName().toString(), path);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case "get":
                        if (parts.length != 3) {
                            System.out.println("Usage: get <name> <path to>");
                            continue;
                        }
                        String filename = parts[1];
                        String pathTo = parts[2];
                        try {
                            db.get(filename, pathTo);
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case "exit":
                        if (parts.length != 1) {
                            System.out.println("Usage: exit");
                            continue;
                        }
                        scanner.close();
                        return;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            }
        } catch (Exception e) {
            System.out.println("FileDB failed with error: " + e.getMessage());
        }
    }
}
