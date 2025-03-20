package com.epam.files;

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
        switch (args[1]) {
            case "1":
                task1();
                break;
            case "2":
                task2();
                break;
            case "3":
                task3();
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

    private static void task1() {
        System.out.println("TODO: Task 1");
    }

    private static void task2() {
        System.out.println("TODO: Task 2");
    }

    private static void task3() {
        System.out.println("TODO: Task 3");
    }

    private static void task4() {
        System.out.println("TODO: Task 4");
    }
}
