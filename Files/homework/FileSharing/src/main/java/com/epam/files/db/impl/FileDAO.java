package com.epam.files.db.impl;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Optional;

public interface FileDAO {
    void saveFile(final String filename, final InputStream content) throws SQLException;
    Optional<FileRecord> retrieveFile(final String filename) throws SQLException;
}
