package com.epam.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class App {
    private final static String helpString =
            """
            Usage:
            $ gradle run --args="<path> <task code>"

            Arguments:
            <path> - a path to the input directory
            <task code> - a code of the task to run from 1 to 4:
                1: Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it;
                2: Print Top-5 largest files by size in bytes;
                3: The average file size in the specified directory or any its subdirectory;
                4: The number of files and folders, divided by the first letters of the alphabet (for example, 100,000 files and 200 folders begin with the letter A).
            """;

    public static void main(String[] args) {
        // Arg 1: path to the input
        // Arg 2: the code of the task to run
        if (args.length != 2) {
            showHelp();
            return;
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path)) {
            System.out.printf("The path " + args[0] + " doesn't exist.");
            return;
        }

        switch (args[1]) {
            case "1":
                task1(path);
                break;
            case "2":
                task2(path);
                break;
            case "3":
                task3(path);
                break;
            case "4":
                task4();
                break;
            default:
                showHelp();
        }
    }

    private static void showHelp() {
        System.out.println(helpString);
    }

    private static void task1(Path path) {
        Optional<Path> res = FileFinder.findFileWithMaxS(path);
        if (res.isPresent()) {
            System.out.println(res.get().getFileName().toString());
        } else {
            System.out.println("No file with letter 's' found.");
        }
    }

    private static void task2(Path path) {
        List<Path> res = FileFinder.findTop5LargestFiles(path);
        for (Path p : res) {
            System.out.println(p.getFileName().toString());
        }
    }

    private static void task3(Path path) {
        System.out.println("Average file size: " + FileFinder.averageFileSize(path) + " bytes.");
    }

    private static void task4() {
        System.out.println("TODO: Task 4");
    }
}
