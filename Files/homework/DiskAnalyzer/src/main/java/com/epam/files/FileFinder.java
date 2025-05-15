package com.epam.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileFinder {
    private FileFinder() {};

    public static Optional<Path> findFileWithMaxS(Path path) {
        long maxCount = 0;
        Path maxFile = null;

        // Traverse paths and find one with max number of 's'
        try (Stream<Path> stream = Files.walk(path)) {
            for (Path file : stream.toList()) {
                if (!Files.isDirectory(file)) {
                    long count = countLetterS(file);
                    if (count > maxCount) {
                        maxCount = count;
                        maxFile = file;
                    }
                }
            }
            return Optional.ofNullable(maxFile);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static long countLetterS(Path file) {
        return countLetterS(file.getFileName().toString());
    }

    private static long countLetterS(String filename) {
        return filename.toLowerCase().chars()
                .filter(c -> c == 's')
                .count();
    }

    public static List<Path> findTop5LargestFiles(Path path) {
        // Traverse to find top 5 largest files
        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    // Exclude count directories
                    .filter(p -> !Files.isDirectory(p))
                    // Sort by size
                    .sorted((p1, p2) -> {
                        try {
                            return Long.compare(Files.size(p2), Files.size(p1));
                        } catch (Exception e) {
                            return 0;
                        }
                    })
                    // Need only 5
                    .limit(5)
                    // Put result into list
                    .toList();
        } catch (Exception e) {
            return List.of();
        }
    }

    public static double averageFileSize(Path path) {
        // Traverse to count average size
        try (Stream<Path> stream = Files.walk(path)) {
            return stream
                    // Count only files
                    .filter(p -> !Files.isDirectory(p))
                    // Take file sizes
                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            return 0;
                        }
                    })
                    .average()
                    // Default value is 0
                    .orElse(0);
        } catch (Exception e) {
            return 0;
        }
    }

    public static Pair<Integer, Integer> countFilesAndDirsStartingWithA(Path path) {
        int filesCount = 0;
        int dirsCount = 0;

        try (var stream = Files.walk(path)) {
            for (Path p : stream.toList()) {
                if (p.getFileName().toString().startsWith("A")
                    || p.getFileName().toString().startsWith("a")) {
                    if (Files.isDirectory(p)) {
                        dirsCount++;
                    } else {
                        filesCount++;
                    }
                }
            }
            return new Pair<>(filesCount, dirsCount);
        } catch (Exception e) {
            return new Pair<>(0, 0);
        }
    }
}
