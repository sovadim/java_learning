package com.epam.files.db.impl;

import com.epam.files.db.errors.FileDBCannotOpenConnectionError;
import com.epam.files.db.errors.FileDBError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.Optional;

public class JDBCFileDAO implements FileDAO, AutoCloseable {
    private static final Logger LOGGER = LogManager.getLogger();

    private final Connection connection;

    public JDBCFileDAO(final String address,
                       final String name,
                       final String user,
                       final String password) throws FileDBError {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://%s/%s".formatted(address, name), user, password
            );
            LOGGER.info("DB connection created");
        } catch (SQLException e) {
            LOGGER.error("Error while creating a DB connection: " + e.getMessage());
            throw new FileDBCannotOpenConnectionError(e.getMessage());
        }
    }

    @Override
    public void saveFile(final String filename,
                         final InputStream content) throws SQLException {
        final String saveSQL = "{call save_file(?, ?)}";
        try (CallableStatement stmt = connection.prepareCall(saveSQL)) {
            stmt.setString(1, filename);
            stmt.setBinaryStream(2, content);
            stmt.execute();
        }
    }

    @Override
    public Optional<FileRecord> retrieveFile(final String filename) throws SQLException {
        final String getSQL = "SELECT * FROM get_file(?)";
        try (CallableStatement stmt = connection.prepareCall(getSQL)) {
            stmt.setString(1, filename);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(new FileRecord(
                            rs.getString("filename"),
                            rs.getBinaryStream("content"),
                            rs.getTimestamp("upload_time")
                    ));
                } else {
                    return Optional.empty();
                }
            }
        }
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
