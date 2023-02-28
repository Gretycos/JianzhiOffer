package lc.labuladong.Others.BruteForce.Q40;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    private int trackSum;
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        trackSum = 0;
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int start, int target){
        // base case，达到目标和，找到符合条件的组合
        if (trackSum == target){
            res.add(new LinkedList<>(track));
            return;
        }
        // base case，超过目标和，直接结束
        if (trackSum > target){
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i-1]){
                continue;
            }
            track.addLast(candidates[i]);
            trackSum += candidates[i];
            backtrack(candidates, i+1, target);
            track.removeLast();
            trackSum -= candidates[i];
        }
    }
}
public class Main {
}
