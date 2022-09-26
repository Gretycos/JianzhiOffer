package lc.hot100.Q51to75.Q239;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int left = 0, right = 0; right < n; right++) {
            while (!queue.isEmpty() && nums[right] > nums[queue.getLast()]){
                queue.removeLast();
            }
            queue.addLast(right);
            if (queue.getFirst() < left){
                queue.removeFirst();
            }
            if (right - k + 1 >= 0){
                res[right-k+1] = nums[queue.getFirst()];
                left++;
            }
        }
        return res;
    }
}
public class Main {
}
