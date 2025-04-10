package com.epam.files;

import com.epam.files.algorithms.FileMoveStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;

public class FileMover {
    private final FileMoveStrategy strategy;

    private static final Logger LOGGER = LogManager.getLogger(FileMover.class);

    public FileMover(FileMoveStrategy strategy) {
        this.strategy = strategy;
    }

    public void move(Path src, Path dst) {
        try {
            strategy.move(src, dst);
        } catch (IOException e) {
            LOGGER.error("Error moving file: {}", e.getMessage());
        }
    }
}
