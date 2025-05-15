package com.epam.files.algorithms;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ChannelFileMoveStrategy implements FileMoveStrategy {
    @Override
    public void move(Path src, Path dst) throws IOException {
        try (var from = FileChannel.open(src, StandardOpenOption.READ);
             var to = FileChannel.open(dst, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW)) {
            from.transferTo(0, from.size(), to);
        }
        Files.delete(src);
    }
}
