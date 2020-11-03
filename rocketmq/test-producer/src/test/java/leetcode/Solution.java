package leetcode;

/**
     y
 x   0 0 0
     0 A 0
     0 0 0
 */
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        return 0;
    }

    public int[] count(String s) {
        int zero = 0, one = 0;
        for(char c : s.toCharArray()) {
            if(c == '0') {
                zero++;
            } else {
                one++;
            }
        }
        return new int[]{zero, one};
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        String[] n = {"10","0001","111001","1","0"};
        System.out.println(s.findMaxForm(n, 5, 3));
    }
}
