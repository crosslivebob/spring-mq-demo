package leetcode;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class LargestNumber_1449 {

    public String largestNumber(int[] cost, int target) {
        if (cost.length != 9) {
            return "error";
        }
        //首先分析出各个数字的成本
        Map<Integer, Integer> costMap = new HashMap<>();
        for (int i = 1; i <= cost.length; i++) {
            costMap.put(i, cost[i-1]);
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        LargestNumber_1449 test = new LargestNumber_1449();
    }
}
