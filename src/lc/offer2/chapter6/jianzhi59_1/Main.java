package lc.offer2.chapter6.jianzhi59_1;

import java.util.*;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 *
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 *
 * 提示：
 *
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        // 结果集
        int[] res = new int[nums.length - k + 1];
        // 结果指针
        int t = 0;

        // 双端队列
        // 存下标
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            // 把超出窗口的元素下标移除
            while (!queue.isEmpty() && i - queue.getFirst() >= k){
                queue.removeFirst();
            }

            // 如果当前元素比队尾元素大，队尾元素移除
            while(!queue.isEmpty() && nums[i] >= nums[queue.getLast()]){
                queue.removeLast();
            }

            // 当前元素入队
            queue.addLast(i);

            if (i >= k - 1){
                res[t++] = nums[queue.getFirst()];
            }

        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(solution.maxSlidingWindow(nums, k)));
    }
}
