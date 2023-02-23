package lc.labuladong.DynamicProgress.greedy.Q435;

import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        return n - intervalSchedule(intervals);
    }

    // 求最多不相交的区间
    private int intervalSchedule(int[][] intervals){
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (itv1, itv2) -> itv1[1] - itv2[1]);
        // 至少有一个区间不相交
        int count = 1;
        // 当前区间结尾
        int xEnd = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start >= xEnd){
                // 找到了下一个选择的区间
                count ++;
                xEnd = interval[1];
            }
        }
        return count;
    }
}

public class Main {
}
