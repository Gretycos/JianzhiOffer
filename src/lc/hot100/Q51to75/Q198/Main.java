package lc.hot100.Q51to75.Q198;

/**
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *     偷窃到的最高金额 = 1 + 3 = 4 。
 *
 *
 * 示例 2：
 *
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * */

class Solution {
    // Time: O(n)
    // Space: O(n)
    // 双指针
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = nums[0];
        f[2] = nums[1];
        int res = Math.max(f[1],f[2]);
        for (int p = 0, i = 3, j = 1; i <= n; i++, j++) {
            if (f[p] < f[j]){
                p = j;
            }
            f[i] = f[p] + nums[i-1];
            res = Math.max(res,f[i]);
        }
        return res;
    }

    // Time: O(n)
    // Space: O(n)
    // dp
    // 定义f(n): 从n个房子中能偷到的最大金额
    // 状态转移：考虑有n-1间房子
    // 多1间房子之后，抢第n间房子则抢不了第n-1间 f(n) = f(n-2) + H(n)
    // 不抢第n间：f(n) = f(n-1)
    // f(n) = max{f(n-1), f(n-2) + H[n-1]}
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = nums[0];
        for (int i = 2; i <= n; i++){
            f[i] = Math.max(f[i-1], f[i-2] + nums[i-1]);
        }
        return f[n];
    }

    // Time: O(n)
    // Space: O(1)
    // dp
    public int rob3(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int fn = 0, fn_1 = 0, fn_2 = 0;
        for (int num : nums) {
            fn = Math.max(fn_1,fn_2 + num);
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,7,9,3,1};
        System.out.println(solution.rob(nums));
    }
}
