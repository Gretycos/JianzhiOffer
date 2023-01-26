package lc.daily.date2301.Q1663;

import java.util.Arrays;

class Solution {
    public String getSmallestString(int n, int k) {
        char[] s = new char[n];
        Arrays.fill(s, 'a');
        int d = k - n;
        for (int i = n - 1; i >= 0; i--) {
            if (d >= 26){
                s[i] = 'z';
                // 把a替换成z，使得剩余数值减少25
                d -= 25;
            }else{
                // 用字符'a' + 1 + d 去替换'a'
                // 需要的字符为 ('a' + 1 + d) - 1
                s[i] = (char) ('a' + d);
                break;
            }
        }
        return String.valueOf(s);
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.getSmallestString(3,27));
    }
}
