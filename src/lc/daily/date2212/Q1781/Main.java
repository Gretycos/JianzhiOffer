package lc.daily.date2212.Q1781;

class Solution {
    public int beautySum(String s) {
        int res = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int[] f = new int[26];
            int maxF = 0;
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                f[c - 'a']++;
                maxF = Math.max(maxF, f[c - 'a']);
                int minF = n;
                for (int freq : f) {
                    if (freq > 0){
                        minF = Math.min(minF, freq);
                    }
                }
                res += maxF - minF;
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "aabcb";
        System.out.println(solution.beautySum(s));
    }
}
