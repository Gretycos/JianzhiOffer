package com.jianzhioffer.chapter5.jianzhi51;

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
//    private boolean[] isVisited;

//    private void dfs(int[] nums, int p){
//        if (isVisited[p] || p == nums.length - 1){
//            return;
//        }
//
//        for (int i = p; i < nums.length-1; i++) {
//            if (nums[p] > nums[i+1]){
//                count++;
//                dfs(nums,i+1);
//            }
//            if (i+1 == nums.length -1){
//                isVisited[p] = true;
//            }
//        }
//    }
//
//    // 回溯剪枝 超时
//    public int reversePairs(int[] nums) {
//        count = 0;
//        isVisited = new boolean[nums.length];
//        int p = 0;
//
//        while(p < nums.length-1){
//            dfs(nums,p);
//            p++;
//        }
//
//        return count;
//    }



    private void merge(int[] nums, int[] temp, int start, int mid, int end){
        int l = mid; // 左边起点
        int r = end; // 右边起点
        int t = end; // 辅助数组索引

        // 从后往前填充
        // 使得目的数组的数字从小到大排列
        // 每个子数组中，后面的数大于前面的数
        while(l >= start && r >= mid + 1){
            //  第一个数组的数字大于第二个数组的数字
            if (nums[l] > nums[r]){
                temp[t--] = nums[l--];
                count +=  r-mid; // 逆序对的数目是后半段剩余的个数
            }else{
                temp[t--] = nums[r--];
            }
        }

        // 左边剩余的
        while(l >= start){
            temp[t--] = nums[l--];
        }

        // 右边剩余的
        while(r >= mid + 1){
            temp[t--] = nums[r--];
        }

        // 把合并后的数组复制回原数组
//        t = end;
//        while(t >= start){
//            nums[t] = temp[t];
//            t--;
//        }
    }
    private void reversePairsCore(int[] nums, int[] temp, int start, int end) {
        if (start >= end){
            return ;
        }

        int length = (end-start)/2;
        int mid = start + length;
        // 拆左边
        // 交换就不需要再复制了
//        int left = reversePairsCore(nums,temp,start,mid);
        reversePairsCore(temp,nums,start,mid);

        // 拆右边
//        int right = reversePairsCore(nums,temp,mid+1,end);
        reversePairsCore(temp,nums,mid+1,end);

        // 合并
        merge(nums,temp,start,mid,end);
    }


    // 归并排序
    public int reversePairs(int[] nums) {
        count = 0;
        int[] temp = Arrays.copyOf(nums,nums.length);
        reversePairsCore(nums, temp,0,nums.length-1);
        return count;
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {8,6,3,5,7,4,2,1};
        System.out.println(solution.reversePairs(nums));
    }
}
