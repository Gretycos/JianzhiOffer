package com.jianzhioffer.chapter6.jianzhi61;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。
 * A 不能视为 14。
 *
 *
 * 示例1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *
 *
 * 示例2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 *
 * 限制：
 *
 * 数组长度为 5
 *
 * 数组的数取值为 [0, 13] .
 *
 * */

class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero = 0;
        for (int i = 0; i < nums.length-1; i++) {
            if (nums[i] == 0){
                zero ++;
            } else {
                int distance = nums[i+1] - nums[i];
                if (distance == 0){
                    return false;
                }
                if (distance > 1){
                    zero -= distance - 1;
                }
            }
        }
        return zero >= 0;
    }
    public boolean isStraight2(int[] nums) {
        int min = 14, max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num: nums){
            // 跳过万能牌
            if (num == 0) continue;
            // 有重复
            if (!set.add(num)) return false;
            min = Math.min(min,num);
            max = Math.max(max,num);
        }
        return max - min < 5;
    }

    public boolean isStraight3(int[] nums) {
        Arrays.sort(nums);
        int min = 14, max = 0;
        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i] != 0 && nums[i-1] == nums[i]){
                return false;
            }
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }
        return max - min <= 5;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,0,8,5,4};
        System.out.println(solution.isStraight3(nums));
    }
}
