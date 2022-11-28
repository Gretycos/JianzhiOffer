package lc.labuladong.DataStructure.tree.Q912;

class Merge{
    private static int[] temp;
    public static void sort(int[] nums){
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int low, int high){
        if (low == high){
            return;
        }

        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high){
        // 先复制到temp，方便后续赋值
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }
        int i = low, j = mid + 1;
        for (int p = low; p<= high; p++){
            if (i == mid + 1){
                // 左半边已经合并完成
                nums[p] = temp[j++];
            }else if (j == high + 1){
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                // 升序排序
                nums[p] = temp[j++];
            }else{
                nums[p] = temp[i++];
            }
        }
    }
}
class Solution {
    public int[] sortArray(int[] nums) {
        Merge.sort(nums);
        return nums;
    }
}

public class Main {
}
