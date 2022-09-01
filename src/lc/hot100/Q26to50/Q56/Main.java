package lc.hot100.Q26to50.Q56;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2：
 *
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 *
 * 提示：
 *
 * 1 <= intervals.length <= 10^4
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 10^4
 *
 * */

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals,(a,b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int n = intervals.length;
        for (int i = 0; i < n; i++) {
            if (i < n - 1 && intervals[i][0] == intervals[i+1][0]){
                continue;
            }
            // 交集
            if (i < n - 1 && intervals[i][1] >= intervals[i+1][0]){
                intervals[i+1][0] = intervals[i][0];
                // 包含
                if (intervals[i][1] > intervals[i+1][1]){
                    intervals[i+1][1] = intervals[i][1];
                }
                continue;
            }
            res.add(intervals[i]);
        }
        return res.toArray(new int[0][0]);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {
                {1,4},{4,5}
        };
        System.out.println(Arrays.deepToString(solution.merge(intervals)));
    }
}
