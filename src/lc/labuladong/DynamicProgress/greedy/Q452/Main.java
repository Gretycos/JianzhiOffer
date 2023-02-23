package lc.labuladong.DynamicProgress.greedy.Q452;

import java.util.Arrays;

class Solution {
    public int findMinArrowShots(int[][] points) {
        return intervalSchedule(points);
    }

    // 求最多不相交的区间
    private int intervalSchedule(int[][] intervals){
        if (intervals.length == 0) return 0;
        // 有溢出问题
        Arrays.sort(intervals, (itv1, itv2) -> itv1[1] > itv2[1] ? 1 : itv1[1] < itv2[1] ? -1 : 0);
        // 至少有一个区间不相交
        int count = 1;
        // 当前区间结尾
        int xEnd = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            if (start > xEnd){ // 端点相交也算重叠
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
