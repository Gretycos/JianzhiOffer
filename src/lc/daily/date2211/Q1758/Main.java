package lc.daily.date2211.Q1758;

class Solution {
    public int minOperations(String s) {
        int times = 0;
        int t = 1;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i) - '0';
            if (c != t){
                times++;
            }
            t ^= 1;
        }
        return Math.min(times, s.length() - times);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0100";
        System.out.println(solution.minOperations(s));
    }
}
