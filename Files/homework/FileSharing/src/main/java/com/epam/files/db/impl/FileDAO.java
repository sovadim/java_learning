package com.epam.files.db.impl;

import java.io.InputStream;
import java.util.Optional;

public interface FileDAO {
    void saveFile(String filename, InputStream content);
    Optional<FileRecord> retrieveFile(String filename);
}
