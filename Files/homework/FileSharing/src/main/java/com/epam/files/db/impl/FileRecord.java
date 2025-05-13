package com.epam.files.db.impl;

import java.io.InputStream;
import java.sql.Timestamp;

public record FileRecord(String filename, InputStream content, Timestamp uploadTime) {}
