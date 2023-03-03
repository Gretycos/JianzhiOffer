package lc.labuladong.Others.Math.Q398;

import java.util.*;

// 时间换空间
class Solution {
    private int[] nums;
    private Random rand;
    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }

    public int pick(int target) {
        int i = 0, p = 0, res = 0;
        while (p < nums.length){
            if (nums[p] == target){
                i++;
                if (rand.nextInt(i) == 0){
                    res = p;
                }
            }
            p++;
        }
        return res;
    }
}

// 空间换时间
class Solution2{
    private Map<Integer, List<Integer>> map;
    private Random rand;
    public Solution2(int[] nums) {
        rand = new Random();
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], key -> new LinkedList<>()).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        return list.get(rand.nextInt(list.size()));
    }
}

public class Main {
}
