package com.algorithm.chapter5.jianzhi40;

import java.util.Arrays;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i]<= 10000
 *
 * */

class Solution {

    private void quickSort(int[] arr, int l, int r, int k){
        if (l >= r){
            return;
        }

        // 记录支点，选第一个元素
        // 第一个元素作为支点会导致，如果是排好序的数组，时间复杂度会变成O(n^2)
        int pivot = arr[l];

        // 选择其中一个元素作为支点的话，需要和第一个元素先交换
        // 交换支点与起点的元素
//        int t = arr[k-1];
//        arr[k-1] = arr[l];
//        arr[l] = t;

        // 左指针和右指针
        int i = l, j = r;
        while (i < j){
            while (i < j && arr[j] >= pivot){
                j--;
            }
            arr[i] = arr[j];

            while (i < j && arr[i] <= pivot){
                i++;
            }
            arr[j] = arr[i];
        }
        arr[i] = pivot;

        // 支点就是第k个元素，说明前k个元素都≤支点
        if (k-1 == i) return;

        // 下标k在左半边
        if (k-1 < i){
            quickSort(arr,l,i-1,k);
        }
        // 下标k在右半边
        else if (k-1 > i){
            quickSort(arr,i+1,r,k);
        }
    }
    // 快排思想 时间O(n) 空间O(logn)
    public int[] getLeastNumbers2(int[] arr, int k){
        int[] res = new int[k];
        if (k == 0){
            return res;
        }
        quickSort(arr,0,arr.length-1,k);
        System.arraycopy(arr, 0, res, 0, k);
        return res;
    }


    // 计数排序 时间O(n) 空间O(n)
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] res = new int[k];
        if (k == 0){
            return res;
        }
        int max = 0;
        for (int num: arr){
            max = Math.max(num, max);
        }
        int[] temp = new int[max+1];
        int p = 0;
        for (int num: arr){
            temp[num] ++;
        }
        for (int i=0; i< temp.length; i++){
            if (temp[i] != 0){
                int count = temp[i];
                while(count > 0 && p < k){
                    res[p++] = i;
                    count--;
                }
            }
            if (p == k){
                break;
            }
        }

        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,0,1,2,4,2,2,3,1,4};
        int k = 5;
        System.out.println(Arrays.toString(solution.getLeastNumbers2(nums, k)));
    }
}
