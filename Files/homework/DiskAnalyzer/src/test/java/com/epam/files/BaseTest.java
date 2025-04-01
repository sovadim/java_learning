package com.epam.files;

import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {
    @TempDir
    public Path tmpDir;

    public void addFiles(String... name) throws IOException {
        for (String n : name) {
            Files.createDirectories(tmpDir.resolve(n).getParent());
            Files.createFile(tmpDir.resolve(n));
        }
    }

    public void addDirs(String... name) throws IOException {
        for (String n : name) {
            Files.createDirectories(tmpDir.resolve(n));
        }
    }

    public void addFileWithLoad(String name, int size) throws IOException {
        Files.createDirectories(tmpDir.resolve(name).getParent());
        var path = Files.createFile(tmpDir.resolve(name));
        try (var out = Files.newOutputStream(path)) {
            out.write("a".repeat(size).getBytes());
        }
    }
}
