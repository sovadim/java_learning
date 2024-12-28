package interfaces;

// Interface Consumer<T>
// https://docs.oracle.com/javase/8/docs/api/java/util/function/Consumer.html

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CollectStringsConsumer implements Consumer<String> {
    private final List<String> collectedStrings = new ArrayList<>();

    @Override
    public void accept(String s) {
        collectedStrings.add(s);
    }

    public List<String> getCollectedStrings() {
        return collectedStrings;
    }
}

public class ConsumerTest {
    @Test
    public void consumerClassTest() {
        CollectStringsConsumer consumer = new CollectStringsConsumer();

        consumer.accept("Hello");
        consumer.accept("World");

        List<String> collected = consumer.getCollectedStrings();
        assertEquals(Arrays.asList("Hello", "World"), collected);
    }
}
