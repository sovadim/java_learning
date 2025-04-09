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
}
