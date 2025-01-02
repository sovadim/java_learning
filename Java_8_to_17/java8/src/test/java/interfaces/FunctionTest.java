package interfaces;

// Interface Function<T,R>
// https://docs.oracle.com/javase/8/docs/api/java/util/function/Function.html

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringLengthFunction implements Function<String, Integer> {
    @Override
    public Integer apply(String s) {
        if (s == null) {
            return -1;
        }
        return s.length();
    }
}

public class FunctionTest {
    @Test
    public void stringLengthFunctionTest() {
        Function<String, Integer> stringLength = new StringLengthFunction();
        assertEquals(5, stringLength.apply("Hello"));
        assertEquals(0, stringLength.apply(""));
        assertEquals(-1, stringLength.apply(null));
    }
}
