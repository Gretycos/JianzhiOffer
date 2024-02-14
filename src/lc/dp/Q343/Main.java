package lc.dp.Q343;

/**
 * @Author Tsong
 * @Date 2024/2/6 11:07
 */

class Solution {
    private int[] memo;
    public int integerBreak(int n) {
        memo = new int[n+1];
        return dp(n);
    }

    private int dp(int i) {
        if (i < 2) return 0;
        if (memo[i] != 0) return memo[i];

        int curMax = 0;
        for (int j = 1; j < i; j++) {
            curMax = Math.max(curMax, Math.max(j * (i - j), j * dp(i - j)));
        }
        memo[i] = curMax;
        return memo[i];
    }
    public int integerBreak2(int n) {
        if (n == 2) return 1;
        if (n == 3) return 2;
        int root = 3;
        int nums = n / root;
        int r = n - root * nums;
        if (r == 0){
            return powerOf(root, nums);
        }
        return Math.max(powerOf(root, nums) * r, powerOf(root, nums-1) * (root+r));
    }

    private int powerOf(int a, int p){
        if (p == 0) return 1;

        if ((p & 1) == 0){
            int temp = powerOf(a, p / 2);
            return temp * temp;
        } else {
            return powerOf(a, p-1) * a;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.integerBreak(58));
    }
}
