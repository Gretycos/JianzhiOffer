package com.algorithm.chapter5.jianzhi51;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 *
 * 限制：
 *
 * 0 <= 数组长度 <= 50000
 *
 * */

class Solution {

    private int count;
    private boolean[] isVisited;

    private void dfs(int[] nums, int p){
        if (isVisited[p] || p == nums.length - 1){
            return;
        }

        for (int i = p; i < nums.length-1; i++) {
            if (nums[p] > nums[i+1]){
                count++;
                dfs(nums,i+1);
            }
            if (i+1 == nums.length -1){
                isVisited[p] = true;
            }
        }
    }

    // 回溯剪枝 超时
    public int reversePairs(int[] nums) {
        count = 0;
        isVisited = new boolean[nums.length];
        int p = 0;

        while(p < nums.length-1){
            dfs(nums,p);
            p++;
        }

        return count;
    }



    private int merge(int[] nums, int[] temp, int start, int mid, int end){
        int i = mid; // 左边起点
        int j = end; // 右边起点
        int t = end; // 辅助数组索引
        int count = 0;

        // 从后往前填充
        // 每个子数组中，后面的数大于前面的数
        while(i >= start && j >= mid + 1){
            //  第一个数组的数字大于第二个数组的数字
            if (nums[i] > nums[j]){
                temp[t--] = nums[i--];
                count +=  j-mid; // 逆序对的数目是后半段剩余的个数
            }else{
                temp[t--] = nums[j--];
            }
        }

        // 左边剩余的
        while(i >= start){
            temp[t--] = nums[i--];
        }

        // 右边剩余的
        while(j >= mid + 1){
            temp[t--] = nums[j--];
        }

        // 把合并后的数组复制回原数组
//        t = end;
//        while(t >= start){
//            nums[t] = temp[t];
//            t--;
//        }

        return count;
    }
    private int reversePairsCore(int[] nums, int[] temp, int start, int end) {
        if (start >= end){
            return 0;
        }

        int length = (end-start)/2;
        int mid = start + length;
        // 拆左边
        // 交换就不需要再复制了
//        int left = reversePairsCore(nums,temp,start,mid);
        int left = reversePairsCore(temp,nums,start,mid);

        // 拆右边
//        int right = reversePairsCore(nums,temp,mid+1,end);
        int right = reversePairsCore(temp,nums,mid+1,end);

        // 合并
        int count = merge(nums,temp,start,mid,end);


        return left+right+count;
    }


    // 归并排序
    public int reversePairs2(int[] nums) {
        int[] temp = Arrays.copyOf(nums,nums.length);
        return reversePairsCore(nums, temp,0,nums.length-1);
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3,2,3,1};
        System.out.println(solution.reversePairs2(nums));
    }
}
