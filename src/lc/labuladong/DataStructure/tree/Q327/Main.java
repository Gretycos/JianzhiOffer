package lc.labuladong.DataStructure.tree.Q327;

class Solution {
    private int lower, upper;
    private long[] temp;
    private int count;
    public int countRangeSum(int[] nums, int lower, int upper) {
        count = 0;
        this.lower = lower;
        this.upper = upper;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i+1] = preSum[i] + nums[i];
        }

        sort(preSum);
        return count;
    }

    private void sort(long[] nums){
        temp = new long[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(long[] nums, int low, int high){
        if (low == high) return;
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid+1, high);
        merge(nums, low, mid, high);
    }

    private void merge(long[] nums, int low, int mid, int high){
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }
        // 在右半边维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
        // 如果 nums[i] 对应的区间是 [start, end)，
        // 因为每半边数组都是递增的
        // 那么 nums[i+1] 对应的区间一定会整体右移，类似滑动窗口
        int start = mid + 1, end = mid + 1;
        for (int i = low; i <= mid; i++) {
            while(start <= high && nums[start] - nums[i] < lower){
                start++;
            }
            while (end <= high && nums[end] - nums[i] <= upper){
                end++;
            }
            count += end - start;
        }

        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++) {
            if (i == mid + 1){
                nums[p] = temp[j++];
            } else if (j == high + 1) {
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else{
                nums[p] = temp[i++];
            }
        }
    }
}

public class Main {
}
