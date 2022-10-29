package lc.daily.date2210.Q907;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 *
 *
 * 示例 2：
 *
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 *
 * */


class Solution {
    // dp
    // dp[i]：以arr[i]结尾的最小值
    // 转移：dp[i] = (i+1) * arr[i], s.isEmpty()
    //            = j * arr[i] + dp[s.getLast()], else (j从i往前开始第一个比它小的数的下标)
    public int sumSubarrayMins(int[] arr) {
        final int MOD = (int)(1e9+7);
        int[] dp = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>(); // 单调栈，因为有负数，滑动窗口不能保证单调性
        int sum = 0;
        for(int i = 0; i < arr.length; i++){
            while(!stack.isEmpty() && arr[stack.getLast()] >= arr[i]){
                stack.removeLast();
            }
            int j = stack.isEmpty() ? i+1 : i-stack.getLast();
            dp[i] = (j * arr[i] + (stack.isEmpty() ? 0 : dp[stack.getLast()])) % MOD;
            sum = (sum + dp[i]) % MOD;
            stack.addLast(i);
        }
        return sum;
    }
}

public class Main {
}
