CREATE OR REPLACE FUNCTION get_file(p_filename VARCHAR)
    RETURNS TABLE (
        filename VARCHAR,
        content BYTEA,
        upload_time TIMESTAMP
    ) AS $$
    BEGIN
        RETURN QUERY
        SELECT f.filename, f.content, f.upload_time
        FROM files f
        WHERE f.filename = p_filename;
    END;
$$ LANGUAGE plpgsql;