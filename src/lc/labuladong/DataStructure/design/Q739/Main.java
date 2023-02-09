package lc.labuladong.DataStructure.design.Q739;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        return nextGreaterElement(temperatures);
    }

    private int[] nextGreaterElement(int[] nums) {
        int[] res = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.getLast()] <= nums[i]){
                stack.removeLast();
            }
            res[i] = stack.isEmpty() ? 0 : stack.getLast() - i;
            stack.addLast(i);
        }
        return res;
    }
}
public class Main {
}
