package sorting;

import java.util.Arrays;

public class MergeSort {

    private static void merge(int[] array, int start, int mid, int end) {
        int right = mid + 1;

        while (start <= mid && right <= end) {
            while (array[start] < array[right]) {
                start++;
            }
            
            if (start <= mid) {
                int k = start;
                while (k <= mid) {
                    swap(array, k, right);
                    k++;
                }
            }
            right++;
        }

        right = mid + 1;
        
        while (right < end && array[right] > array[right + 1]) {
            swap(array, right, right + 1);
            right++;
        }
    }

    // utils!!!
    private static void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private static void order(int[] array, int start, int end) {
        if (start != end) {
            if (end - start == 1) {
                if (array[end] < array[start])
                    swap(array, start, end);
            }
            else {
                int mid = (end + start) / 2;
                order(array, start, mid);
                order(array, mid + 1, end);
                merge(array, start, mid, end);
            }
        }
    }

    public static void sort(int[] array) {
        if (array.length > 0) {
            order(array, 0, array.length - 1);
        }
    }
    public static void main(String[] args) {
        int[] toSort = new int[] {-12, 32, 0, 4, -24, 98, 74, -81, -3};

        sort(toSort);

        System.out.println(Arrays.toString(toSort));
    }
}
