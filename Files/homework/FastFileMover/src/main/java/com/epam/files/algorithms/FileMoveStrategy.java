package com.epam.files.algorithms;

import java.io.IOException;
import java.nio.file.Path;

public interface FileMoveStrategy {
    void move(Path src, Path dst) throws IOException;
}
