package lc.daily.date2302.Q1234;

class Solution {
    public int balancedString(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'A']++;
        }
        int balance = n / 4;
        if (isBalanced(count, balance)) return 0;

        int res = n;
        for (int l = 0, r = 0; l < n; l++) {
            while (r < n && !isBalanced(count, balance)){
                count[s.charAt(r) - 'A'] --;
                r++;
            }
            if (!isBalanced(count, balance)) break;
            res = Math.min(res, r - l);
            count[s.charAt(l) - 'A'] ++;
        }
        return res;
    }

    private boolean isBalanced(int[] count, int balance){
        return count['Q' - 'A'] <= balance && count['W' - 'A'] <= balance
                && count['E' - 'A'] <= balance && count['R' - 'A'] <= balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "QQQWWQRR";
        System.out.println(solution.balancedString(s));
    }
}
