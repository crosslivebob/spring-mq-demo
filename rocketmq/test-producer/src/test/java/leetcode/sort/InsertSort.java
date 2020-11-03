package leetcode.sort;

public class InsertSort implements ISort {
    @Override
    public void sort(int[] arrays) {
        for (int i = 1; i < arrays.length; i++) {
            int j = i;
            int tmp = arrays[i];
            while(j > 0 && tmp < arrays[j - 1]) {
                arrays[j] = arrays[j - 1];
                j--;
            }
            if (j != i) {
                arrays[j] = tmp;
            }
        }
    }
}
