package com.epam.files.db.errors;

public class FileDBRetrieveError extends FileDBError {
    public FileDBRetrieveError(String message) {
        super("Failed to retrieve file with message: " + message);
    }
}
