import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MergeSortFJPTest {
    @Test
    void sortEmptyArray() {
        int[] array = {};
        int[] expected = {};
        MergeSortFJP.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void sortWithSingleElement() {
        int[] array = {42};
        int[] expected = {42};
        MergeSortFJP.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void sortSortedArray() {
        int[] array = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSortFJP.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void sortReverseSortedArray() {
        int[] array = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        MergeSortFJP.sort(array);
        assertArrayEquals(expected, array);
    }

    @Test
    void sortArrayOfRandoms() {
        var random = new Random();
        int[] array = random.ints(10_000, 0, 100_000).toArray();
        int[] expected = array.clone();
        Arrays.sort(expected);

        MergeSortFJP.sort(array);
        assertArrayEquals(expected, array);
    }
}
