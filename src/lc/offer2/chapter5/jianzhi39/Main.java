package lc.offer2.chapter5.jianzhi39;

import java.util.HashMap;
import java.util.Map;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 *
 * 限制：
 *
 * 1 <= 数组长度 <= 50000
 *
 * */

class Solution {


    // 摩尔投票法
    // 时间O(n)，空间O(1)
    public int majorityElement2(int[] nums){
        int result = nums[0];
        int times = 0;
        for (int num : nums) {
            if (times == 0){
                result = num;
                times = 1;
            } else {
                times += num == result ? 1 : -1;
            }
        }
        return result;
    }

    // 哈希表
    // 时间O(n)，空间O(n)
    public int majorityElement(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) == null){
                map.put(num, 1);
                continue;
            }
            int value = map.get(num);
            map.put(num, ++value);
            if (value > nums.length / 2){
                return num;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,2,2,2,5,4,2};
        System.out.println(solution.majorityElement(nums));
    }
}
