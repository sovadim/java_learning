package base64;

import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base64Test {
    private String encode(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes());
    }

    private String decode(String input) {
        return new String(Base64.getDecoder().decode(input));
    }

    @Test
    public void encodeTest() {
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        String actual = encode("Hello, World!");
        assertEquals(expected, actual);
    }

    @Test
    public void decodeTest() {
        String expected = "Hello, World!";
        String actual = decode(encode(expected));
        assertEquals(expected, actual);
    }
}
