package com.epam.files.db;

import com.epam.files.db.errors.FileDBError;
import com.epam.files.db.impl.FileRecord;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class FileDB implements AutoCloseable {
    private final Connection connection;

    public FileDB(String address,
                  String name,
                  String user,
                  String password) throws FileDBError {
        // TODO: create connection
    }

    public void add(String filename, String pathFrom) throws FileDBError {
        // TODO: save file
    }

    public Optional<FileRecord> get(String name, String pathTo) throws FileDBError {
        // TODO: retrieve file
        return Optional.empty();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("DB connection closing thrown error: " + e.getMessage());
        }
    }
}
