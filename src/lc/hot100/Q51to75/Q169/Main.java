package lc.hot100.Q51to75.Q169;

/**
 * 给定一个大小为 n 的数组nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于⌊ n/2 ⌋的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例1：
 *
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 *
 * 示例2：
 *
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 * */

class Solution {
    public int majorityElement(int[] nums) {
        int res = nums[0], count = 1;
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == res){
                count++;
            }else{
                if (--count == 0){
                    res = nums[i];
                    count = 1;
                }
            }
        }
        return res;
    }
}
public class Main {
}
