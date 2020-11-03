package leetcode.sort;

import org.junit.Before;
import org.junit.Test;

public class TestSort {

    private int[] arrays = {9, 5, 6, 2, 10};

    private ISort sort;

    @Before
    public void setUp() {
        //堆排序
//        sort = new HeapSort();
        //快速排序
//        sort = new QuickSort();
        //插入排序
        sort = new InsertSort();
    }

    @Test
    public void testSort() {
        long before = System.nanoTime();
        sort.sort(arrays);
        System.out.println("cost time: " + (System.nanoTime() - before));
        print(arrays);
    }

    public void print(int[] arrays) {
        for (int i : arrays) {
            System.out.print(i + "\t");
        }
    }

}
