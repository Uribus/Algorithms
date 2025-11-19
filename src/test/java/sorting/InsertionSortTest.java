package sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Arrays;

public class InsertionSortTest {
    private static final int SIZE = 15;
    private static final Random random = new Random(42);
    private InsertionSort sorter = new InsertionSort();

    private static int[] generateValues(int size, int max, int min) {
        int[] array = new int[size];

        for (int i = 0; i < size; i ++)
            array[i] = min + random.nextInt(max - min + 1);

        return array;
    }

    @Test
    public void assertArrayNegativesOnlySorted() {
        int[] arrayNegatives = generateValues(SIZE, -1, -100);
        int[] copy = arrayNegatives.clone();

        Arrays.sort(copy);
        sorter.sort(arrayNegatives);

        assertArrayEquals(arrayNegatives, copy);
    }

    @Test
    public void assertArrayPositivesOnlySorted() {
        int[] arrayPositives = generateValues(SIZE, 100, 1);
        int[] copy = arrayPositives.clone();

        Arrays.sort(copy);
        sorter.sort(arrayPositives);

        assertArrayEquals(arrayPositives, copy);
    }

    @Test
    public void assertArrayIntegersSorted() {
        int[] array = generateValues(SIZE, 145, -200);
        int[] copy = array.clone();

        Arrays.sort(copy);
        sorter.sort(array);

        assertArrayEquals(array, copy);
    }
}
