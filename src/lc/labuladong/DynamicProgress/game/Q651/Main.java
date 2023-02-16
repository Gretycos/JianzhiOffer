package lc.labuladong.DynamicProgress.game.Q651;

class Solution{
    public int maxA(int N){
        // 定义：dp[i] 为 i次操作后能显示多少个A
        int[] dp = new int[N+1];
        // base case
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            // dp[i] = max(这次按A，这次按C-A、C-C、若干C-V)

            // 先尝试按一次A
            dp[i] = dp[i-1] + 1;
            // j 作为 C-V 的起点，而前两个操作一定是C-A C-C
            for (int j = 2; j < i; j++) {
                // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
                // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
                dp[i] = Math.max(dp[i], dp[j-2] * (i - j + 1));
            }
        }
        return dp[N];
    }

}
public class Main {
}
