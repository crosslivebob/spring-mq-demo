package leetcode.sort;

public class QuickSort implements ISort {
    @Override
    public void sort(int[] arrays) {
        quickSort(arrays, 0, arrays.length - 1);
    }

    private void quickSort(int[] arrays, int left, int right) {
        if (left < right) {
            int pttn = partition(arrays, left, right);
            quickSort(arrays, left, pttn - 1);
            quickSort(arrays, pttn + 1, right);
        }
    }

    private int partition(int[] arrays, int left, int right) {
        int pivot = left;
        int index = pivot + 1;
        for (int i = index; i <= right; i++) {
            if (arrays[i] < arrays[pivot]) {
                swap(arrays, i, index);
                index++;
            }
        }
        swap(arrays, pivot, index - 1);
        return index - 1;
    }

    private void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
