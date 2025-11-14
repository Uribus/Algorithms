package sorting;

public class InsertionSort {
    private int[] array;

    public InsertionSort() {}

    public InsertionSort(int[] array) {
        this.array = array;
    }

    public int[] getArray() { return this.array; }

    // move shift to utils? use swap?
    private static void shiftRight(int[] nums, int start, int end) {
        int toSwap = nums[end];
        for (; start <= end; start++) {
            nums[start] = nums[start] ^ toSwap;
            toSwap = toSwap ^ nums[start];
            nums[start] = toSwap ^ nums[start];
        }
    }

    public void sort(int[] toSort) {
        for (int i = 1; i < toSort.length; i++) {
            int elem = toSort[i];
            int j = 0;
            while (j < i && elem > toSort[j]) {
                j++;
            }

            if (j < i) {
                shiftRight(toSort, j, i);
            }
        }

    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        int[] arr = new int[] {-11, -13, -15, -5, -76, 0, -458, -32, -70};
        sorter.sort(arr);

        for (int num : arr)
            System.out.print(num + " ");

    }
}
