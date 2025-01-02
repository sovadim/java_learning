package interfaces;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Functional interface:
 *  - an interface with only one abstract method
 *  - can contain default and static methods
 *  - can contain abstract methods from Object class
 *  - usable for functional programming
 */

@FunctionalInterface
interface Workable {
    String work();
}

class Worker implements Workable {
    @Override
    public String work() {
        return "Worker is working";
    }
}

public class FunctionalTest {
    @Test
    public void workableIsWorkingTest() {
        String expected = "Worker is working";

        Workable workable = new Worker();
        String actual = workable.work();

        assertEquals(expected, actual);
    }
}
