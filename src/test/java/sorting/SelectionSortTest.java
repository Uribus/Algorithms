package sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Arrays;


public class SelectionSortTest {
    
    private final SelectionSort sorter = new SelectionSort();
    private static final int SIZE = 15;
    private static final Random random = new Random(41);

    private static int[] generateValues(int size, int min, int max) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(max - min + 1) + min;
        }

        return array;
    }

    @Test
    public void assertArrayNegativesOnlyOrdered() {
        int[] negatives = generateValues(SIZE, -100, -1);
        int[] copy = negatives.clone();
        
        sorter.sort(negatives);
        Arrays.sort(copy);

        assertArrayEquals(negatives, copy);
    }
    
    @Test
    public void assertArrayPositivesOnlyOrdered() {
        int[] positives = generateValues(SIZE, 1, 100);
        int[] copy = positives.clone();

        sorter.sort(positives);
        Arrays.sort(copy);

        assertArrayEquals(positives, copy);
    }

    @Test
    public void assertArrayOrdered() {
        int[] array = generateValues(SIZE, -50, 50);
        int[] copy = array.clone();

        sorter.sort(array);
        Arrays.sort(copy);

        assertArrayEquals(array, copy);
    }
}
