CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    content BYTEA NOT NULL,
    upload_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);