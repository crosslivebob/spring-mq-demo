package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.function.DoubleFunction;
import java.util.function.LongFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LeetCodeTest {
    public static List<Integer> lexicalOrder(int n) {
        TreeSet<String> set = new TreeSet<>();
        for (int i = 1; i <= n; i++) {
            set.add(String.valueOf(i));
        }
        List<Integer> result = set.stream().map(Integer::valueOf).collect(Collectors.toList());
        return result;
    }
    public static void main(String[] args) {
        System.out.println(lexicalOrder(13));
    }
}
