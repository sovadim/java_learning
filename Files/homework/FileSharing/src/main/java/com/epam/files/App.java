package com.epam.files;

import com.epam.files.db.FileDB;

public class App {
    private final static String helpString =
            """
            File Sharing App
            Usage:
            $ gradle run --args="<address> <db name> <user> <password>"
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
            // TODO: REPL with commands
            // - add file from path
            // - retrieve file to path
        } catch (Exception e) {
            System.out.println("FileDB failed with error: " + e.getMessage());
        }
    }
}
