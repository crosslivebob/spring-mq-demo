package leetcode.sort;

public class HeapSort implements ISort {

    @Override
    public void sort(int[] arrays) {
        int len = arrays.length;
        //建立大顶堆
        buildMaxHeap(arrays, len);

        for (int i = len - 1; i > 0; i--) {
            swap(arrays, 0 , i);
            //最后一个已是最大值，下次heapify不用在考虑它
            len--;
            heapify(arrays, 0, len);
        }
    }

    private void buildMaxHeap(int[] arrays, int len) {
        for (int i = len / 2; i >= 0; i--) {
            heapify(arrays, i, len);
        }
    }

    private void heapify(int[] arrays, int i, int len) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;
        if(left < len && arrays[left] > arrays[largest]) {
            largest = left;
        }
        if (right < len && arrays[right] > arrays[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arrays, i, largest);
            heapify(arrays, largest, len);
        }
    }

    private void swap(int[] arrays, int i, int j) {
        int temp = arrays[i];
        arrays[i] = arrays[j];
        arrays[j] = temp;
    }
}
