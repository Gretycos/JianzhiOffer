package lc.daily.date2301.Q1803;

class Solution {
    private int count;
    private int[] temp;
    private int low, high;
    public int countPairs(int[] nums, int low, int high) {
        temp = new int[nums.length];
        this.low = low;
        this.high = high;
        sort(nums, 0, nums.length - 1);
        return count;
    }

    private void sort(int[] nums, int lo, int hi){
        if (lo == hi){
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid+1, hi);
        merge(nums, lo, mid, hi);

    }
    private void merge(int[] nums, int lo, int mid, int hi){
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        for (int i = lo; i <= mid; i++) {
            for (int j = mid + 1; j <= hi; j++) {
                int xor = nums[i] ^ nums[j];
                if (xor >= low && xor <= high){
                    count++;
                }
            }
        }

        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1){
                nums[p] = temp[j++];
            }else if (j == hi + 1){
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {7881,760,709,2937,1245,720,5187,6361,3793,141,7238};
        int low = 1492, high = 3832;
        System.out.println(solution.countPairs(nums,low,high));
    }
}
