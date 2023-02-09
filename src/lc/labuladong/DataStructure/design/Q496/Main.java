package lc.labuladong.DataStructure.design.Q496;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res2 = nextGreaterElement(nums2);
        int[] res = new int[nums1.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = res2[nums1[i]];
        }
        return res;
    }

    private int[] nextGreaterElement(int[] nums) {
        int[] res = new int[10001];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.getLast() <= nums[i]){
                stack.removeLast();
            }
            res[nums[i]] = stack.isEmpty() ? -1 : stack.getLast();
            stack.addLast(nums[i]);
        }
        return res;
    }
}

public class Main {
}
