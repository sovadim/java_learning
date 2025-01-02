package time;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LocalDateTimeTest {
    @Test
    public void localDateFieldsTest() {
        LocalDate ld = LocalDate.of(2024, Month.DECEMBER, 29);
        assertEquals(2024, ld.getYear());
        assertEquals(Month.DECEMBER, ld.getMonth());
        assertEquals(12, ld.getMonthValue());
        assertEquals(29, ld.getDayOfMonth());
        assertEquals("2024-12-29", ld.toString());
    }

    @Test
    public void localTimeFieldsTest() {
        LocalTime lt = LocalTime.of(23, 59, 59);
        assertEquals(23, lt.getHour());
        assertEquals(59, lt.getMinute());
        assertEquals(59, lt.getSecond());
        assertEquals("23:59:59", lt.toString());
    }

    @Test
    public void localDateTimeFieldsTest() {
        LocalDateTime ldt = LocalDateTime.of(2024, Month.DECEMBER, 29, 23, 59, 59);
        assertEquals(2024, ldt.getYear());
        assertEquals(Month.DECEMBER, ldt.getMonth());
        assertEquals(12, ldt.getMonthValue());
        assertEquals(29, ldt.getDayOfMonth());
        assertEquals(23, ldt.getHour());
        assertEquals(59, ldt.getMinute());
        assertEquals(59, ldt.getSecond());
        assertEquals("2024-12-29T23:59:59", ldt.toString());

        LocalDate ld = LocalDate.of(2024, Month.DECEMBER, 29);
        LocalTime lt = LocalTime.of(23, 59, 59);
        LocalDateTime ldt2 = LocalDateTime.of(ld, lt);
        assertEquals(ldt, ldt2);
    }

    @Test
    public void timeComparisonTest() {
        LocalDateTime ldt1 = LocalDateTime.of(2022, Month.JANUARY, 1, 1, 0, 0);
        LocalDateTime ldt2 = LocalDateTime.of(2024, Month.DECEMBER, 30, 23, 59, 59);
        LocalDateTime ldt3 = LocalDateTime.of(2024, Month.DECEMBER, 30, 23, 59, 59);
        assertTrue(ldt1.isBefore(ldt2));
        assertTrue(ldt2.isAfter(ldt1));
        assertTrue(ldt2.isEqual(ldt3));
    }

    @Test
    public void dateTimeFormattingTest() {
        LocalDateTime ldt = LocalDateTime.of(2024, Month.DECEMBER, 30, 23, 59, 59);
        assertEquals(
            "20241230",
            DateTimeFormatter.BASIC_ISO_DATE.format(ldt)
        );
        assertEquals(
            "2024-12-30",
            DateTimeFormatter.ISO_LOCAL_DATE.format(ldt)
        );
        assertEquals(
            "2024-12-30Z",
            DateTimeFormatter.ISO_OFFSET_DATE.format(ldt.atOffset(ZoneOffset.UTC))
        );
        assertEquals(
            "2024-12-30",
            DateTimeFormatter.ISO_DATE.format(ldt)
        );
        assertEquals(
            "23:59:59",
            DateTimeFormatter.ISO_LOCAL_TIME.format(ldt)
        );
        assertEquals(
            "23:59:59Z",
            DateTimeFormatter.ISO_OFFSET_TIME.format(ldt.atOffset(ZoneOffset.UTC))
        );
        assertEquals(
            "23:59:59",
            DateTimeFormatter.ISO_TIME.format(ldt)
        );
        assertEquals(
            "2024-12-30T23:59:59",
            DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(ldt)
        );
        assertEquals(
            "2024-12-30T23:59:59Z",
            DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(ldt.atOffset(ZoneOffset.UTC))
        );
        assertEquals(
            "2024-12-30T23:59:59Z",
            DateTimeFormatter.ISO_ZONED_DATE_TIME.format(ldt.atOffset(ZoneOffset.UTC))
        );
        assertEquals(
            "2024-12-30T23:59:59",
            DateTimeFormatter.ISO_DATE_TIME.format(ldt)
        );
        assertEquals(
            "2024-365",
            DateTimeFormatter.ISO_ORDINAL_DATE.format(ldt)
        );
        assertEquals(
            "2025-W01-1",
            DateTimeFormatter.ISO_WEEK_DATE.format(ldt)
        );
        assertEquals(
            "2024-12-30T23:59:59Z",
            DateTimeFormatter.ISO_INSTANT.format(ldt.atOffset(ZoneOffset.UTC))
        );
        assertEquals(
            "Mon, 30 Dec 2024 23:59:59 GMT",
            DateTimeFormatter.RFC_1123_DATE_TIME.format(ldt.atOffset(ZoneOffset.UTC))
        );
    }

    @Test
    public void customDateTimeFormattingTest() {
        LocalDateTime ldt = LocalDateTime.of(2024, Month.DECEMBER, 30, 23, 59, 59);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm:ss");
        assertEquals("30-December-2024 23:59:59", dtf.format(ldt));
    }
}
