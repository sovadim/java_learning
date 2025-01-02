package time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeriodTest {
    @Test
    public void periodFieldsTest() {
        Period p = Period.of(1, 2, 3);
        assertEquals(1, p.getYears());
        assertEquals(2, p.getMonths());
        assertEquals(3, p.getDays());
    }

    @Test
    public void betweenTest() {
        LocalDate ld1 = LocalDate.of(2000, 1, 1);
        LocalDate ld2 = LocalDate.of(2002, 4, 5);
        Period p = Period.between(ld1, ld2);
        assertEquals(2, p.getYears());
        assertEquals(3, p.getMonths());
        assertEquals(4, p.getDays());

        // In case we need distance in days
        assertEquals(825, ChronoUnit.DAYS.between(ld1, ld2));
    }
}
