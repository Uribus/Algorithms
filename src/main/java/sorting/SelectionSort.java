package sorting;

public class SelectionSort {

    int[] array;

    public SelectionSort() {}

    public SelectionSort(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    // move swap to utils?
    private static void swap(int[] array, int posI, int posMin) {
        int temp = array[posI];
        array[posI] = array[posMin];
        array[posMin] = temp;
    } 

    public void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;

            // get the minimum non-sorted element
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }

            swap(array, i, min);
        }
    }
    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        int[] toSort = {13, 55, 2, -3, 73, 22, 57, -41, 47, 0};

        sorter.sort(toSort);
        
        for (int elem : toSort) 
            System.out.print(elem + " ");
    }
}