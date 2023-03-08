package lc.labuladong.Others.Classic.Q56;

import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res = new LinkedList<>();
        // 按区间的 start 升序排列
        Arrays.sort(intervals, (a,b)->a[0]-b[0]);
        res.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            // res 中最后一个元素的引用
            int[] last = res.getLast();
            if (cur[0] <= last[1]){
                // 找到最大的 end
                last[1] = Math.max(last[1], cur[1]);
            } else {
                // 处理下一个待合并区间
                res.add(cur);
            }
        }
        return res.toArray(new int[0][]);
    }
}

public class Main {
}
