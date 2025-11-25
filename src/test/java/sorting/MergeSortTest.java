package sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Arrays;


public class MergeSortTest {

    private static final int SIZE = 15;
    private static final Random random = new Random(41);

    private static int[] generateValues(int max, int min) {
        if (max < min) {
            int temp = max;
            max = min;
            min = temp;
        }
        int[] values = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            values[i] = random.nextInt(max - min + 1) + min;
        }

        return values;
    }

    @Test
    public void assertArrayIntegerNegativesOnlyOrdered() {
        int[] array = generateValues(-1, -100);
        int[] copy = array.clone();

        MergeSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(array, copy);
    }

    @Test
    public void assertArrayIntegerPositivesOnlyOrdered() {
        int[] array = generateValues(100, 1);
        int[] copy = array.clone();

        MergeSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(array, copy);
    }

    @Test
    public void assertArrayIntegersOrdered() {
        int[] array = generateValues(150, -150);
        int[] copy = array.clone();

        MergeSort.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(array, copy);
    }

}
