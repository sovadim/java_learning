package com.epam.files.algorithms;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NIOFileMoveStrategy implements FileMoveStrategy {
    @Override
    public void move(Path source, Path destination) throws IOException {
        Files.move(source, destination);
    }
}
