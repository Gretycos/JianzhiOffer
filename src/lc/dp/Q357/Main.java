package lc.dp.Q357;

/**
 * @Author Tsong
 * @Date 2024/2/8 11:33
 */

class Solution {
    private int n;
    private boolean[] used;
    public int countNumbersWithUniqueDigits2(int n) {
        if (n == 0) return 1;
        this.n = n;
        used = new boolean[10];
        return dfs(0);
    }
    
    private int dfs(int i) {
        if (i == n) return 0;
        int nums = 0;
        for (int j = 0; j < 10; j++) {
            if (i == 1 && j == 0 && n > 1) continue;
            if (!used[j]){
                used[j] = true;
                nums += dfs(i+1) + 1;
                used[j] = false;
            }
        }
        return nums;
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        int nums = 10;
        int cur = 9 * 9;
        for (int i = 2; i <= n; i++) {
            nums += cur;
            cur *= 10 - i;
        }
        return nums;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countNumbersWithUniqueDigits(8));
    }
}
