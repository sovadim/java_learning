CREATE OR REPLACE FUNCTION save_file(p_filename VARCHAR, p_content BYTEA)
    RETURNS void AS $$
    DECLARE
        file_id INTEGER;
    BEGIN
        INSERT INTO files (filename, content)
        VALUES (p_filename, p_content);
    END;
$$ LANGUAGE plpgsql;