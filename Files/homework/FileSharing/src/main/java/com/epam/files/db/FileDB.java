package com.epam.files.db;

import com.epam.files.db.errors.FileDBCannotOpenConnection;
import com.epam.files.db.errors.FileDBError;
import com.epam.files.db.impl.FileRecord;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class FileDB implements AutoCloseable {
    private final Connection connection;

    public FileDB(final String address,
                  final String name,
                  final String user,
                  final String password) throws FileDBError {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://%s/%s".formatted(address, name), user, password
            );
        } catch (SQLException e) {
            throw new FileDBCannotOpenConnection(e.getMessage());
        }
    }

    public void add(final String filename,
                    final String pathFrom) throws FileDBError {
        // TODO: save file
    }

    public Optional<FileRecord> get(final String name,
                                    final String pathTo) throws FileDBError {
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
