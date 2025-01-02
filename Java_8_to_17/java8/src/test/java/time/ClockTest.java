package time;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClockTest {
    @Test
    public void defaultZoneTest() {
        Clock clock = Clock.systemDefaultZone();
        assertEquals("SystemClock[Asia/Yerevan]", clock.toString());
    }

    @Test
    public void instantTest() {
        Clock clock1 = Clock.systemDefaultZone();
        Clock clock2 = Clock.systemUTC();
        System.out.println(clock1.instant());
        System.out.println(clock2.instant());
    }

    @Test
    public void offsetTest() {
        Clock clock1 = Clock.systemDefaultZone();
        Clock clock2 = Clock.offset(clock1, Duration.ofHours(1));
        System.out.println(clock1.instant());
        System.out.println(clock2.instant());
    }
}
