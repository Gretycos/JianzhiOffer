package com.hot100.Q42;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 *
 * 提示：
 *
 * n == height.length
 * 1 <= n <= 2 * 10^4
 * 0 <= height[i] <= 10^5
 *
 * */
class Solution {
    public int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int Lmax = 0, Rmax = 0;
        int sum = 0;
        // 对于某一个位置i, 我们能否接这个位置上的雨水, 取决于i左右两侧的最大值(leftMax, rightMax)是否比height[i]大.
        // 只有当i左右两侧的最大值都比height[i]大时, 才能接i上的雨水, 数量为min(leftMax, rightMax) - height[i];
        // 这里得出结论, 限制当前位置接雨水的条件是其左右两侧最大值中的较小值min(leftMax, rightMax).

        //对于l和r两个指针, l从左向右移动, r从右向左移动, 对于指针l来说, leftMax是真实可信的,
        // 因为leftMax的值是l一步一个脚印走出来的, 但是rightMax是不真实不可信的,
        // 因为l不知道从height[i]到height[j]之间是否有其他的数大于rightMax;
        // 同样,对于r来说rightMax是真实可信的, leftMax值是不真实不可信的.
        // 这里得出结论: 对于左指针l, 它右侧的真实的最大值 >= rightMax, 对于右指针r, 它左侧的真实的最大值 >= leftMax.
        while(l < r){
            Lmax = Math.max(Lmax,height[l]);
            Rmax = Math.max(Rmax,height[r]);
            // 对于位置left而言，它左边最大值一定是left_max，右边最大值“大于等于”right_max，
            // 这时候，如果left_max<right_max成立，那么它就知道自己能存多少水了。
            // 无论右边将来会不会出现更大的right_max，都不影响这个结果。
            // 所以当left_max<right_max时，我们就希望去处理left下标，反之，我们希望去处理right下标。
            if (Lmax < Rmax){
                sum += Lmax - height[l];
                l++;
            }else{
                sum += Rmax - height[r];
                r--;
            }
        }
        return sum;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(solution.trap(height));
    }
}
