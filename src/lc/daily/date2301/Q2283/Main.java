package lc.daily.date2301.Q2283;

class Solution {
    public boolean digitCount(String num) {
        int[] map = new int[10];
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            map[c - '0']++;
        }
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) - '0' != map[i]) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.digitCount("1210"));
    }
}
