package com.epam.files.db.errors;

public class FileDBError extends RuntimeException {
    public FileDBError(String message) {
        super(message);
    }
}
