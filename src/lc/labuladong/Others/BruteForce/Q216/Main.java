package lc.labuladong.Others.BruteForce.Q216;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<Integer>> res;
    private LinkedList<Integer> track;
    private int k, n;

    public List<List<Integer>> combinationSum3(int k, int n) {
        res = new LinkedList<>();
        track = new LinkedList<>();
        this.k = k;
        this.n = n;

        int min = 0;
        for (int i = 1; i <= k; i++) {
            min += i;
        }
        if (min > n) return res;

        backtrack(1, 0);
        return res;
    }

    private void backtrack(int start, int sum){
        if (track.size() == k){
            if (sum == n){
                res.add(new LinkedList<>(track));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (sum + i > n){
                continue;
            }
            track.addLast(i);
            backtrack(i+1, sum + i);
            track.removeLast();
        }
    }
}

public class Main {
}
