package lc.daily.date2210.Q915;

/**
 * 给定一个数组nums，将其划分为两个连续子数组left和right，使得：
 *
 * left中的每个元素都小于或等于right中的每个元素。
 * left 和right都是非空的。
 * left 的长度要尽可能小。
 * 在完成这样的分组后返回left的长度。
 *
 * 用例可以保证存在这样的划分方法。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [5,0,3,8,6]
 * 输出：3
 * 解释：left = [5,0,3]，right = [8,6]
 *
 *
 * 示例 2：
 *
 * 输入：nums = [1,1,1,0,6,12]
 * 输出：4
 * 解释：left = [1,1,1,0]，right = [6,12]
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^6
 * 可以保证至少有一种方法能够按题目所描述的那样对 nums 进行划分
 *
 * */

class Solution {
    public int partitionDisjoint(int[] nums) {
        int lMax = nums[0], curMax = nums[0];
        int cutPoint = 0; // 分割点，隔板放置在分隔板的右边
        for (int i = 1; i < nums.length; i++) {
            curMax = Math.max(curMax,nums[i]);
            // 在右边遇到比lMax更小的，就要扩充区间，同时把lMax更新成curMax的值
            if (nums[i] < lMax){
                cutPoint = i;
                lMax = curMax;
            }
        }
        return cutPoint + 1;
    }
}
public class Main {
}
