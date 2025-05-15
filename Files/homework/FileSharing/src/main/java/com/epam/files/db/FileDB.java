package com.epam.files.db;

import com.epam.files.db.errors.*;
import com.epam.files.db.impl.FileRecord;
import com.epam.files.db.impl.JDBCFileDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

public class FileDB implements AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private final JDBCFileDAO dao;

    public FileDB(final String address,
                  final String name,
                  final String user,
                  final String password) throws FileDBError {
        dao = new JDBCFileDAO(address, name, user, password);
    }

    public void add(final String filename,
                    final String pathFrom) throws FileDBError {
        try (InputStream stream = Files.newInputStream(Path.of(pathFrom))) {
            dao.saveFile(filename, stream);
        } catch (Exception e) {
            LOGGER.error("Error while saving file: " + e.getMessage());
            throw new FileDBAddError(e.getMessage());
        }
    }

    public void get(final String name,
                    final String pathTo) throws FileDBError {
        try {
            Optional<FileRecord> record = dao.retrieveFile(name);
            if (record.isEmpty()) {
                throw new FileDBNotFoundError(name);
            }
            Files.copy(
                    record.get().content(),
                    Path.of(pathTo),
                    StandardCopyOption.REPLACE_EXISTING
            );
        } catch (Exception e) {
            LOGGER.error("Error while retrieving file: " + e.getMessage());
            throw new FileDBRetrieveError(e.getMessage());
        }
    }

    @Override
    public void close() {
        dao.close();
    }
}
