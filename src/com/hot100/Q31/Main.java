package com.hot100.Q31;

import java.util.Arrays;

/**
 * 整数数组的一个 排列 就是将其所有成员以序列或线性顺序排列。
 *
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，
 * 那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 * 示例 2：
 *
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 * 示例 3：
 *
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 * */

class Solution {
    private int[] a;
    private int n;
    public void nextPermutation(int[] nums) {
        if (nums.length == 1) return;
        a = nums;
        n = nums.length;
        int i = n - 1;
        // 寻找升序
        while(i > 0 && a[i-1] >= a[i]){
            i--;
        }
        // 最后两个数字是升序的，直接交换最后两个数字
        if (i == n-1){
            swap(i,i-1);
            return;
        }
        // 整个数组是降序排序的
        if (i == 0){
            reverse(0);
            return;
        }
        // 数组一部分出现升序a[i-1,i]，a[i~n]为降序
        if(i > 0){
            // 在i-1后查询比a[i-1]的数大的最小的数
            int nextIdx = findNext(i-1);
            // 交换这这两个数
            swap(i-1,nextIdx);
            // 此时a[i~n]依旧为降序
            // 把a[i~n]变为升序就找到了下一个排列
            reverse(i);
        }
    }
    private void swap(int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private int findNext(int start){
        // 从start~n-1查询最小的比a[start]大的数
        for(int i = n-1; i > start; i --){
            if (a[i] > a[start]){
                return i;
            }
        }
        return -1;
    }

    // 降低时间复杂度的关键
    private void reverse(int start){
        int i = start, j = n-1;
        while(i < j){
            swap(i++,j--);
        }
    }
}
public class Main {
}
