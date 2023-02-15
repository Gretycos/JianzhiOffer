package lc.labuladong.DynamicProgress.game.Q486;

class Pair{
    int first, second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        // 定义：dp[i][j].first 为 先手从[i,j]中拿取得的最大分数
        //      dp[i][j].second 为 后手从[i,j]中拿取得的最大分数
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0,0);
            }
        }

        // base case
        // i == j的时候，先手拿之后，就不剩下石头了
        for (int i = 0; i < n; i++) {
            dp[i][i].first = nums[i];
        }

        // 因为结果在右上角，所以需要先从下到上遍历，再从左到右遍历
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 先手分别选择左、右得到的分数
                int left = nums[i] + dp[i+1][j].second;
                int right = nums[j] + dp[i][j-1].second;

                // 先手要选分数高的
                if (left > right){
                    dp[i][j].first = left; // 本轮的先手
                    dp[i][j].second = dp[i+1][j].first; // 本轮的后手在下轮是先手
                } else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j-1].first;
                }
            }
        }
        Pair res = dp[0][n-1];
        return res.first >= res.second;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,5,2};
        System.out.println(solution.PredictTheWinner(nums));
    }
}
