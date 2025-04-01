package com.epam.files;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class FileFinder {
    public Optional<Path> findFileWithMaxS(Path path) {
        // Path can either a file or a directory
        if (!Files.isDirectory(path)) {
            return countLetterS(path) > 0 ? Optional.of(path) : Optional.empty();
        }
        // Traverse paths and find one with max number of 's'
        long maxCount = 0;
        Path maxFile = null;

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

    private long countLetterS(Path file) {
        return countLetterS(file.getFileName().toString());
    }

    private long countLetterS(String filename) {
        return filename.toLowerCase().chars()
                .filter(c -> c == 's')
                .count();
    }
}
