package lc.labuladong.DataStructure.tree.Q96;

class Solution {
    private int[][] cache;
    public int numTrees(int n) {
        cache = new int[n+1][n+1];
        return count(1, n);
    }

    private int count(int lo, int hi){
        if (lo > hi) return 1;
        if (cache[lo][hi] != 0){
            return cache[lo][hi];
        }

        int res = 0;
        for (int mid = lo; mid <= hi; mid++) {
            int left = count(lo, mid - 1);
            int right = count(mid + 1, hi);
            res += left * right;
        }

        cache[lo][hi] = res;
        return res;
    }
}

public class Main {
}
