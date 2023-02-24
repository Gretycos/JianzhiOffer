package lc.daily.date2302.Q2357;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int minimumOperations(int[] nums) {
        int res = 0;
        while (true){
            int min = 101;
            boolean allZeros = true;
            for (int num : nums) {
                if (num > 0) {
                    allZeros = false;
                    min = Math.min(num, min);
                }
            }
            if (allZeros) return res;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0){
                    int diff = nums[i] - min;
                    nums[i] = Math.max(diff, 0);
                }
            }
            res ++;
        }
    }

    public int minimumOperations2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0){
                set.add(num);
            }
        }
        return set.size();
    }
}

public class Main {
}
