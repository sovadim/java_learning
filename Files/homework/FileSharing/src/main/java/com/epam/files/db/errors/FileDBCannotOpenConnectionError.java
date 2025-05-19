package com.epam.files.db.errors;

public class FileDBCannotOpenConnectionError extends FileDBError {
    public FileDBCannotOpenConnectionError(String message) {
        super("Can't open FileDB with message: " + message);
    }
}
