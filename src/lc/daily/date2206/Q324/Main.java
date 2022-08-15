package lc.daily.date2206.Q324;

import java.util.Arrays;

/**
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 *
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 * 示例 2：
 *
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 *
 * */

class Solution {
    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
    private void threeWaysSort(int[] nums, int start, int end, int k){
        if (start >= end)
            return;
        int pivot = nums[start];
        // nums[start+1..lt-1) < pivot
        // nums[lt-1..gt] == pivot
        // nums(gt..end] > pivot
        // lt-1 是 相等区间的第一个位置, gt 是相等区间的最后一个位置
        int lt = start + 1, i = start + 1, gt = end;
        while(i <= gt){
            if (nums[i] < pivot){
                swap(nums,i++,lt++);
            }else if (nums[i] > pivot){
                // 交换过来的值还要判断位于哪个区间
                swap(nums,i,gt--);
            }else{
                i++;
            }
        }
        swap(nums,start,lt-1);

        if (k < lt - 1){
            threeWaysSort(nums,start,lt-2,k);
        } else if (k > gt){
            threeWaysSort(nums,gt+1,end,k);
        }

    }

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int mid = (n+1) / 2; // 上取整
        threeWaysSort(nums,0,n-1,mid);
        int[] temp = nums.clone();
        for(int i = 0, j = mid-1, k = n-1; i < n; i+=2, j--, k--){
            nums[i] = temp[j];
            if (i + 1 < n){
                nums[i + 1] = temp[k];
            }
        }
    }

    // 理论上低效，但实际高效
    public void wiggleSort2(int[] nums) {
        int n = nums.length;
        int[] temp = nums.clone();
        Arrays.sort(temp);
        int p = n - 1;
        for (int i = 1; i < n; i += 2){
            nums[i] = temp[p--];
        }
        for (int i = 0; i < n; i += 2){
            nums[i] = temp[p--];
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,5,1,1,6,4};
        solution.wiggleSort(nums);
    }
}
