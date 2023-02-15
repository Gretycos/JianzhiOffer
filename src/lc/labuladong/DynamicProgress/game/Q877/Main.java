package lc.labuladong.DynamicProgress.game.Q877;

class Pair{
    int first, second;
    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }
}
class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {

                dp[i][j] = new Pair(0,0);
            }
        }

        // base case
        for (int i = 0; i < n; i++) {
            dp[i][i].first = piles[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                // 先手
                int left = piles[i] + dp[i+1][j].second;
                int right = piles[j] + dp[i][j-1].second;

                if (left > right){
                    dp[i][j].first = left;
                    dp[i][j].second = dp[i+1][j].first;
                } else {
                    dp[i][j].first = right;
                    dp[i][j].second = dp[i][j-1].first;
                }
            }
        }
        Pair res = dp[0][n-1];
        return res.first > res.second;
    }
}
public class Main {
}
