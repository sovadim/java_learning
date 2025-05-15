package com.epam.files.db.errors;

public class FileDBAddError extends FileDBError {
    public FileDBAddError(String message) {
        super("Failed to add file with message: " + message);
    }
}
