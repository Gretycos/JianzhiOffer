package com.algorithm;

import java.util.Arrays;

public class Order {

    // 快速排序
    private static void quickSort(int[] nums, int left, int right){
        if (left>=right) return; // 只剩一个元素认为已经排序好了

        int i = left, j = right; // 左右指针

        // 记录支点元素
        int pivot = nums[i];
        while (i < j){
            // 右指针往左寻找一个比支点小的或相等（处理重复）的元素
            while (i < j && nums[j] >= pivot){
                j--;
            }
            // 填入左指针的位置
            nums[i] = nums[j];
            // 左指针往右寻找一个比支点大的或相等（处理重复）的元素
            while (i < j && nums[i] <= pivot){
                i++;
            }
            // 填入右指针的位置
            nums[j] = nums[i];
        }
        // 把支点元素填入
        // 此时i==j
        nums[i] = pivot;
        // 数组传参是传引用
        // 排序支点左边
        quickSort(nums,left,i-1);
        // 排序支点右边
        quickSort(nums,i+1,right);

    }


    // 快速排序，不稳定，时间O(nlogn)，空间O(logn)
    static int[] QuickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3,3,4,5,1,2,8,9};
        System.out.println(Arrays.toString(QuickSort(nums)));
    }
}
