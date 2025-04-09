package com.epam.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    private final static String helpString =
            """
            Usage:
            $ gradle run --args="<file path> <new directory path> <algorithm code>"
            
            Arguments:
            - <file path> - path to the file to be moved
            - <new directory path> - new path to file
            - <algorithm code> - algorithm code (1, 2, 3 or 4)
                - 1: Using FileStreams
                - 2: Using FileStreams with buffer 100 kb
                - 3: Using FileChannel
                - 4: Using NIO 2 File API
            """;

    public static void main(String[] args) {
        // Arg 1: path to file
        // Arg 2: new path to file
        // Arg 3: algorithm code
        if (args.length != 3) {
            System.out.println(helpString);
            return;
        }

        Path filePath = Paths.get(args[0]);
        if (!Files.exists(filePath)) {
            System.out.println("File '" + filePath + "' does not exist");
            return;
        }

        Path newPath = Paths.get(args[1]);
        if (Files.exists(newPath)) {
            System.out.println("File '" + newPath + "' already exist");
            return;
        }

        int algorithmCode = Integer.parseInt(args[2]);
        if (algorithmCode < 1 || algorithmCode > 4) {
            System.out.println("Algorithm code must be 1, 2, 3 or 4");
            return;
        }

        run(filePath, newPath, algorithmCode);
    }

    private static void run(Path file, Path dir, int algorithmCode) {
        // TODO
    }
}
