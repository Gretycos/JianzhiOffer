package lc.labuladong.DataStructure.dp.Q416;

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 不能分成等和的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        // 目标是sum / 2
        sum /= 2;
        // 定义dp[i][j]：对前i个物品，背包容量为j时，恰好装满的情况
        boolean[][] dp = new boolean[n+1][sum+1];
        // base case
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // 背包空间为0，相当于装满了
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (j - nums[i - 1] < 0){
                    // 容量不足，不装入
                    dp[i][j] = dp[i - 1][j];
                }else{
                    // 装入或不装入
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][sum];
    }

    // 优化空间
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 不能分成等和的集合
        if (sum % 2 != 0) return false;
        int n = nums.length;
        // 目标是sum / 2
        sum /= 2;
        boolean[] dp = new boolean[sum+1];

        // base case
        dp[0] = true;

        for (int i = 0; i < n; i++) {
            // 从右到左遍历的原因：
            // 回忆原来2D情况，dp[i][j]的值都是依靠“其正上方的值dp[i-1][j]+左上方的值dp[i-1][j-nums[i]]”来更新。
            // 那么如果对1D进行正向遍历即从dp[0]->dp[n-1]填充，
            // 对于某一位例如dp[cur]的更新，势必会用到dp[pre]（pre<cur），
            // 因为是正向遍历，那么dp[pre]在当前轮次已经被更新过了，
            // 当在这种情况下计算的dp[cur]肯定不正确
            // （其实说白了，就相当于2D情况下，使用了同一行的值。例如使用dp[i][j-nums[i]]来更新dp[i][j]）
            for (int j = sum; j >= 0; j--) {
                if (j - nums[i] >= 0){
                    dp[j] = dp[j] || dp[j - nums[i]];
                }
            }
        }

        return dp[sum];
    }
}
public class Main {
}
