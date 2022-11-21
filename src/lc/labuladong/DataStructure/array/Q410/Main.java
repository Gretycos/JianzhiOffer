package lc.labuladong.DataStructure.array.Q410;

class Solution {
    public int splitArray(int[] nums, int m) {
        // 当m == 1, 数组的最大值为 sum
        // 当m == n, 数组的最大值 max(nums[i])
        int left = 0, right = 0;
        for (int num : nums) {
            if (left < num) left = num;
            right += num;
        }
        while (left <= right){
            int mid = left + (right - left) / 2;
            int needM = getM(nums, mid);
            if (needM == m){
                right = mid - 1;
            }else if (needM > m){ // 分的组太多了，说明和太小了，要增大
                left = mid + 1;
            }else if (needM < m){
                right = mid - 1; // 分的组不够，说明和太大了，要缩小
            }
        }
        return left;
    }

    private int getM(int[] nums, int sum){
        int curSum = 0;
        int m = 1;
        for (int num : nums) {
            if (curSum + num > sum){
                m++;
                curSum = num;
            }else{
                curSum += num;
            }
        }
        return m;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4,5};
        solution.splitArray(nums, 2);
    }
}
