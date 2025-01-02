package interfaces;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Interface Predicate<T>
// https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html

class IsNumber42Predicate implements Predicate<Integer> {
    @Override
    public boolean test(Integer number) {
        return number == 42;
    }
}

public class PredicateTest {
    @Test
    public void predicateClassTest() {
        Predicate<Integer> isNumber42 = new IsNumber42Predicate();
        assertTrue(isNumber42.test(42));
        assertFalse(isNumber42.test(43));
    }

    @Test
    public void predicateAnonymousClassTest() {
        Predicate<Integer> isNumber42 = new Predicate<Integer>() {
            @Override
            public boolean test(Integer number) {
                return number == 42;
            }
        };
        assertTrue(isNumber42.test(42));
        assertFalse(isNumber42.test(43));
    }
}
