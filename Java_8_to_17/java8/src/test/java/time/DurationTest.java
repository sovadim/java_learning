package time;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DurationTest {
    @Test
    public void durationFieldsTest() {
        Duration d = Duration.of(1, ChronoUnit.DAYS);
        assertEquals(1, d.toDays());
        assertEquals(24, d.toHours());
        assertEquals(1440, d.toMinutes());
        assertEquals(86400, d.getSeconds());
        assertEquals(86400000, d.toMillis());
        assertEquals(0, d.getNano());
    }

    @Test
    public void betweenTest() {
        LocalTime lt1 = LocalTime.of(1, 1, 1);
        LocalTime lt2 = LocalTime.of(2, 2, 2);
        Duration d = Duration.between(lt1, lt2);
        assertEquals(1, d.toHours());
        assertEquals(61, d.toMinutes());
        assertEquals(3661, d.getSeconds());
    }
}
