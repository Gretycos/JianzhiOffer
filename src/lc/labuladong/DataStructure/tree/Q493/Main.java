package lc.labuladong.DataStructure.tree.Q493;

class Solution {
    private int count;
    private int[] temp;
    public int reversePairs(int[] nums) {
        count = 0;
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return count;
    }

    private void sort(int[] nums, int low, int high){
        if (low == high) return;
        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private void merge(int[] nums, int low, int mid, int high){
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }

        // 因为每一半都是有序的
        // 所以对于nums[i]，如果nums[i] > nums[j] * 2
        // 那么一定有nums[i+1] > nums[j] * 2
        for (int i = low, j = mid + 1; i <= mid; i++) {
            while (j <= high && (long) nums[i] > (long)nums[j] * 2){
                j++;
            }
            count += j - (mid + 1);
        }

        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++) {
            if (i == mid + 1){
                nums[p] = temp[j++];
            } else if (j == high + 1) {
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                nums[p] = temp[j++];
            }else {
                nums[p] = temp[i++];
            }
        }
    }
}

public class Main {
}
