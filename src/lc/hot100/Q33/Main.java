package lc.hot100.Q33;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为[4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回-1。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例2：
 *
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 *
 * */

class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        // 二分查找只能用在有序数组中，所以需要寻找变化之后的有序的部分
        while(l <= r){
            int m = l + (r - l) / 2;
            if (target == nums[m]){
                return m;
            }
            if (nums[m] > nums[r]){ // 左半段有序
                if (target < nums[m] && target >= nums[l]){
                    r = m - 1;
                }else{
                    l = m + 1;
                }
            }else{ // 右半段有序
                if (target > nums[m] && target <= nums[r]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
        }
        return -1;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {4,5,6,7,8,1,2,3};
        int target = 2;
        System.out.println(solution.search(nums,target));
    }
}
