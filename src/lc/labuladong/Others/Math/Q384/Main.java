package lc.labuladong.Others.Math.Q384;

import java.util.Arrays;
import java.util.Random;

class Solution {
    private int[] nums;
    private Random random;
    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int n = nums.length;
        int[] copy = Arrays.copyOf(nums, n);
        for (int i = 0; i < n; i++) {
            // 生成一个 [i, n-1] 区间内的随机数
            int r = i + random.nextInt(n-i);
            swap(copy, i, r);
        }
        return copy;
    }

    private void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}

public class Main {
}
