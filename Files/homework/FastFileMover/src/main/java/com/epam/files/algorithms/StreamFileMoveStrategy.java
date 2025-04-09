package com.epam.files.algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StreamFileMoveStrategy implements FileMoveStrategy {
    final int bufferSize;

    public StreamFileMoveStrategy() {
        this(256);
    }

    public StreamFileMoveStrategy(int bufferSize) {
        this.bufferSize = bufferSize;
    }

    @Override
    public void move(Path src, Path dst) throws IOException {
        try (var from = new FileInputStream(src.toFile());
             var to = new FileOutputStream(dst.toFile())) {

            final byte[] buffer = new byte[bufferSize];
            int count;

            while ((count = from.read(buffer)) != -1) {
                to.write(buffer, 0, count);
            }
        }
        Files.delete(src);
    }
}
