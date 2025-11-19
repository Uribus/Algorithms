package sorting;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;



public class MergeSortTest {

    private MergeSort sorter = new MergeSort();
    private static final int SIZE = 15;
    private static final Random random = new Random(41);

    private static int[] generateValues(int max, int min) {
        int[] values = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            values[i] = random.nextInt(max - min + 1) + min;
        }

        return values;
    }

}
