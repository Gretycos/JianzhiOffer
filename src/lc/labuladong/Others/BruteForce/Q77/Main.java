package lc.labuladong.Others.BruteForce.Q77;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    public List<List<Integer>> combine(int n, int k) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        backtrack(1, n, k);
        return res;
    }

    private void backtrack(int start, int n, int k){
        if (track.size() == k){
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = start; i <= n; i++) {
            track.addLast(i);
            backtrack(i+1, n, k);
            track.removeLast();
        }
    }
}
public class Main {
}
