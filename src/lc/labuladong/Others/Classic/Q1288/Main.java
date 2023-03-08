package lc.labuladong.Others.Classic.Q1288;

import java.util.Arrays;

class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals,(a,b) -> a[0]-b[0] != 0 ? a[0]-b[0] : b[1]-a[1]);
        int left = intervals[0][0], right = intervals[0][1];
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            if (left <= intv[0] && right >= intv[1]){
                res ++;
            } else if (right >= intv[0] && right <= intv[1]){
                right = intv[1];
            } else if (right < intv[1]){
                left = intv[0];
                right = intv[1];
            }
        }
        return intervals.length - res;
    }
}
public class Main {
}
