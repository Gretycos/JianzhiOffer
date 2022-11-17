package lc.labuladong.DataStructure.array.Q34;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1,-1};
        int n = nums.length;
        if (n == 0) return res;
        // 左侧
        // 搜索区间：[left, right]
        int left = 0, right = n - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] == target){
                right = mid - 1;
            }
        }
        // 退出时，left == right + 1
        if (left != n){
            res[0] = nums[left] == target ? left : -1;
        }
        // 右侧
        // 搜索区间：[left, right]
        left = 0; right = n - 1;
        while(left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] < target){
                left = mid + 1;
            }else if (nums[mid] > target){
                right = mid - 1;
            }else if (nums[mid] == target){
                left = mid + 1;
            }
        }
        // 退出时，right == left - 1
        if (right != -1){
            res[1] = nums[right] == target ? right : -1;
        }

        return res;
    }
}
public class Main {
}
