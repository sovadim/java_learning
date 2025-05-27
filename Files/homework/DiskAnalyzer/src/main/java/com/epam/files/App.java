package com.epam.files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class App {
    private static Path outPath;
    private static final Logger LOGGER = LogManager.getLogger();

    private final static String helpString =
            """
            Usage:
            $ gradle run --args="<path> <task code> <output file [optional]>"

            Arguments:
            <path> - a path to the input directory
            <task code> - a code of the task to run from 1 to 4:
                1: Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it;
                2: Print Top-5 largest files by size in bytes;
                3: The average file size in the specified directory or any its subdirectory;
                4: The number of files and folders, divided by the first letters of the alphabet (for example, 100,000 files and 200 folders begin with the letter A).
            <output file> - a path to the output file, by default is out.txt
            """;

    public static void main(String[] args) {
        // Arg 1: path to the input
        // Arg 2: the code of the task to run
        try {
            if (args.length != 2
                && args.length != 3) {
                showHelp();
                return;
            }

            Path path = Paths.get(args[0]);
            if (!Files.exists(path)) {
                System.out.println("The path " + args[0] + " doesn't exist.");
                return;
            }

            if (args.length == 3) {
                outPath = Paths.get(args[2]);
            } else {
                outPath = Paths.get("out.txt");
            }

            switch (args[1]) {
                case "1" -> task1(path);
                case "2" -> task2(path);
                case "3" -> task3(path);
                case "4" -> task4(path);
                default -> showHelp();
            }
        } catch (IOException e) {
            LOGGER.error("Error writing to output file: " + e.getMessage());
        }
    }

    private static void showHelp() throws IOException {
        System.out.println(helpString);
    }

    private static void task1(Path path) throws IOException {
        writeResult(FileFinder.findFileWithMaxS(path)
                .map(p -> p.getFileName().toString())
                .orElse("No file with letter 's' found."));
    }

    private static void task2(Path path) throws IOException {
        List<Path> res = FileFinder.findTop5LargestFiles(path);
        writeResult(res.stream()
                .map(p -> p.getFileName().toString())
                .collect(Collectors.joining("\n"))
        );
    }

    private static void task3(Path path) throws IOException {
        writeResult("Average file size: " + FileFinder.averageFileSize(path) + " bytes.");
    }

    private static void task4(Path path) throws IOException {
        Pair<Integer, Integer> res = FileFinder.countFilesAndDirsStartingWithA(path);
        writeResult(res.value1() + " files and " + res.value2() + " folders begin with the letter A");
    }

    private static void writeResult(String text) throws IOException {
        Files.createFile(outPath);
        Files.write(outPath, text.getBytes());
    }
}
