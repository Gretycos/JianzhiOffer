package lc.daily.date2211.Q805;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定你一个整数数组nums
 *
 * 我们要将nums数组中的每个元素移动到A数组 或者B数组中，使得A数组和B数组不为空，并且average(A) == average(B)。
 *
 * 如果可以完成则返回true， 否则返回 false。
 *
 * 注意：对于数组arr, average(arr)是arr的所有元素的和除以arr长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7,8]
 * 输出: true
 * 解释: 我们可以将数组分割为 [1,4,5,8] 和 [2,3,6,7], 他们的平均值都是4.5。
 * 示例 2:
 *
 * 输入: nums = [3,1]
 * 输出: false
 *
 *
 * 提示:
 *
 * 1 <= nums.length <= 30
 * 0 <= nums[i] <= 10^4
 *
 * */

class Solution {
    public boolean splitArraySameAverage(int[] nums) {
        if (nums.length == 1) return false;
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 由于sum(A) / k == sum(B) / (n - k)
        // 得出sum(nums) / n == sum(A) / k
        // 问题转化为：在nums中找到一个子数组A，使得avg(A) == avg(nums)
        // 如果nums[i] = nums[i] - avg(nums)，这样使得avg(nums) == 0
        // 那么问题转化为：在nums中找到一个子数组A，使得avg(A) == 0
        // 但是avg(nums)在转化前可能是浮点数，计算比较会带来误差
        // 所以等式左右两边扩大n倍
        // nums[i] = nums[i] * n - avg(nums) * n == nums[i] * n - sum(nums)
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * n - sum;
        }

        // nums一分为二
        // 从前半个数组中的m个元素中取出若干元素构成子集，共2^m种方法
        // 每个子集的结果用left存储
        int m = n / 2;
        Set<Integer> left = new HashSet<>();
        // 枚举2^m种情况，相当于位运算
        for (int situation = 1; situation < 1 << m; situation++) {
            int sumLeft = 0;
            // 遍历前m个元素
            for (int i = 0; i < m; i++) {
                // 假如situation == 6，二进制表示为110
                // 则 sumLeft = nums[2] + nums[3]
                if ((situation & (1 << i)) != 0){ // 第i位是1，则求和
                    sumLeft += nums[i];
                }
            }
            if (sumLeft == 0) return true; // 找到和为0，满足条件
            left.add(sumLeft);
        }

        // 右半部分有2^(n-m)种情况
        for (int situation = 1; situation < 1 << (n-m); situation++) {
            int sumRight = 0;
            // 遍历后面的n-m个元素
            for (int i = m; i < n; i++) {
                // 假如situation == 6, 二进制为110
                // 则说明从第m个元素开始的第2、3号元素需要求和
                if ((situation & 1 << (i - m)) != 0){
                    sumRight += nums[i];
                }
            }
            if (sumRight == 0 // 找到和为0的子数组，直接返回
                    // 或者右边元素没被全选而且在左边找到了相反数，说明左边一部分与右边一部分的和为0

                    // 由于整个数组和为0，所以前半个数组的和肯定是后半个数组和的相反数；
                    // 如果前半个数组的真子集的元素之和也是后半个数组和的相反数，
                    // 那么前半个数组真子集的补集的和就为0，
                    // 那么在计算left的情况时就返回答案了。

                    // 为什么要排除右边全选 因为你如果右边全选的，而因为前面已经记录过左边全选的情况的，那么必然有left.contains(-rightSum)。
                    // 因为全集合（全左+全右）也为0，而左边全选然后加上右边全选，就等于全集了，
                    // 而将全集作为A数组，那么B数组长度为0了，则不符合题意了。
                    || (situation != ((1 << (n-m)) - 1) && left.contains(-sumRight))){
                return true;
            }
        }
        return false;
    }
}
public class Main {
}
