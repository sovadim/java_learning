package com.epam.files.db.impl;

import java.io.InputStream;
import java.util.Optional;

public class JDBCFileDAO implements FileDAO {
    @Override
    public void saveFile(String filename, InputStream content) {
        // TODO
    }

    @Override
    public Optional<FileRecord> retrieveFile(String filename) {
        // TODO
        return Optional.empty();
    }
}
