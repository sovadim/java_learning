package interfaces;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Interface Supplier<T>
// https://docs.oracle.com/javase/8/docs/api/java/util/function/Supplier.html

class IncrementalNumberSupplier implements Supplier<Integer> {
    private int currentValue = 0;

    @Override
    public Integer get() {
        return currentValue++;
    }
}

public class SupplierTest {
    @Test
    public void incrementalNumberSupplierTest() {
        Supplier<Integer> incrementalNumber = new IncrementalNumberSupplier();
        assertEquals(0, incrementalNumber.get());
        assertEquals(1, incrementalNumber.get());
        assertEquals(2, incrementalNumber.get());
    }
}
