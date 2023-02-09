package lc.labuladong.DataStructure.design.Q503;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // 数组翻倍 + 取模 实现循环效果
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.getLast() <= nums[i % n]){
                stack.removeLast();
            }
            res[i % n] = stack.isEmpty()? -1 : stack.getLast();
            stack.addLast(nums[i % n]);
        }
        return res;
    }
}

public class Main {
}
