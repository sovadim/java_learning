package com.epam.files.db.errors;

public class FileDBNotFoundError extends FileDBError {
    public FileDBNotFoundError(String name) {
        super("File '" + name + "' not found.");
    }
}
