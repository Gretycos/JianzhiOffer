package lc.labuladong.DataStructure.tree.Q215;

import java.util.Random;

class Solution{
    public int findKthLargest(int[] nums, int k) {
        shuffle(nums);
        int lo = 0, hi = nums.length - 1;
        // 转化成查询升序的第k'名
        k = nums.length - k;
        while (lo <= hi){
            int p = partition(nums, lo, hi);
            if (p < k){
                lo = p + 1;
            }else if (p > k){
                hi = p - 1;
            }else if (p == k){
                return nums[p];
            }
        }
        return -1;
    }

    private int partition(int[] nums, int lo, int hi){
        int pivot = nums[lo];
        int i = lo + 1, j = hi;
        while (i <= j){
            while (i < hi && nums[i] <= pivot){
                i++;
            }
            while (j > lo && nums[j] > pivot){
                j--;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, lo, j);
        return j;
    }

    private void shuffle(int[] nums){
        Random random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
public class Main {
}
