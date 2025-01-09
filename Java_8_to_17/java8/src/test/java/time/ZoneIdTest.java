package time;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZoneIdTest {
    @Test
    public void zoneIdsEnumerationTest() {
        for (String zoneId : java.time.ZoneId.getAvailableZoneIds()) {
            System.out.println(zoneId);
        }
    }

    @Test
    public void zoneIdDisplayTest() {
        ZoneId zoneId = ZoneId.of("Europe/London");
        assertEquals("Europe/London", zoneId.getId());
        assertEquals("British Time", zoneId.getDisplayName(TextStyle.FULL, Locale.US));
        assertEquals("BT", zoneId.getDisplayName(TextStyle.SHORT, Locale.US));
    }

    @Test
    public void zonedDateTimeTest() {
        ZoneId zoneId = ZoneId.of("US/Pacific");
        LocalDateTime ldt = LocalDateTime.of(2024, 12, 29, 23, 59, 59);
        ZonedDateTime zdt = ZonedDateTime.of(ldt, zoneId);
        assertEquals("2024-12-29T23:59:59", ldt.toString());
        assertEquals("2024-12-29T23:59:59-08:00[US/Pacific]", zdt.toString());
    }
}
