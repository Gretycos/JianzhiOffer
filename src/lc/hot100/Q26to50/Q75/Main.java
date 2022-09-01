package lc.hot100.Q26to50.Q75;

/**
 * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 * 示例 2：
 *
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 * */

class Solution {
    public void sortColors(int[] nums) {
        int lt = 0, gt = nums.length - 1;
        int i = 0;
        int pivot = 1;
        // gt--之后是一个新位置，因此还没扫描过，所以i==gt的时候循环继续
        while(i <= gt){
            if (nums[i] < pivot){
                swap(nums,i++,lt++);
            }else if (nums[i] > pivot){
                // 换到i的位置的数字可能是0 1 2，所以i不能动
                // 但是换到gt位置的一定是比pivot大的数，因此gt--
                swap(nums,i,gt--);
            }else{
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2,0,1};
    }
}
