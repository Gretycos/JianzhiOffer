package lc.hot100.Q128;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 *
 * 请你设计并实现时间复杂度为O(n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * 示例 2：
 *
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * */

class Solution {
    // 哈希表，理论上时间复杂度低
    // T：O(n)，S:O(n)
    // 325ms
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num: nums){
            set.add(num);
        }
        int length = 0;
        for(int num: nums){
            // 如果这个数前面没有别的数字，说明是一个起点
            if (!set.contains(num-1)){
                int i = num;
                // 从起点往后遍历寻找连续的数字
                while(set.contains(i)) i++;
                // 更新长度
                length = Math.max(length, i - num);
            }
        }
        return length;
    }

    // 实际上，数据量不足的情况下，哈希表没有排序快
    // 12ms
    public int longestConsecutive2(int[] nums) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        int max = 1, length = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i+1]){
                continue;
            }else if (nums[i] + 1 == nums[i+1]){
                length ++;
            }else{
                length = 1;
            }
            max = Math.max(max,length);
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,0,1};
        System.out.println(solution.longestConsecutive2(nums));
    }
}
