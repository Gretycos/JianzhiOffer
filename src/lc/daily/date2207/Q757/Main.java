package lc.daily.date2207.Q757;

import java.util.Arrays;

/**
 * 一个整数区间[a, b](a < b) 代表着从a到b的所有连续整数，包括a和b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 *
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals的长度范围为[1, 3000]。
 * intervals[i]长度为2，分别代表左、右边界。
 * intervals[i][j] 的值是[0, 10^8]范围内的整数。
 *
 * */

class Solution {
    // 坑点：选择的元素不一定要连续的
    // time: O(nlogn)
    // space: O(1)
    public int intersectionSizeTwo(int[][] intervals) {
        // 按左端点升序排序，如果左端点相等则按右端点降序排序
        // 例如[2,3],[3,4],[5,10],[5,8]中，
        // 对于[5,10]区间来说，只要满足了和[5,8]的交集数量>=2，则必定满足和[5,10]交集数量>=2
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int n = intervals.length;
        // 选择两个元素 满足pos1 < pos2
        // 开始时，选择最后一个区间的左端点和左端点+1
        // 对于一个区间[a,b]来说，有a < b
        // 当区间长度为最小值2时，其形式为[a,a+1]，选择的初始元素一定能产生交集数量为2
        int pos1 = intervals[n - 1][0];
        int pos2 = intervals[n - 1][0] + 1;
        // 交集的大小
        int size = 2;
        // 从后向前遍历
        for (int i = n - 2; i >= 0; i--) {
            // 分类讨论
            // 对于新区间[xi,yi]，根据排序规则一定有 xi <= pos1

            // 如果yi >= pos2，则区间覆盖了pos1, pos2两个元素，交集大小一定>=2
            if (intervals[i][1] >= pos2) {
                continue;
            }
            // 如果yi < pos1，则区间与pos1, pos2无交集
            // 此时需要新选择两个元素，pos1=xi,pos2=xi+1
            // 使其满足交集大小为2的条件
            // size+=2
            if (intervals[i][1] < pos1) {
                pos1 = intervals[i][0];
                pos2 = intervals[i][0] + 1;
                size += 2;

            // 如果 pos1 <= yi < pos2，则区间与pos1, pos2只有1个交集
            // 此时需要让pos1保留（因为产生了交集），并且使其为新选择的两个元素的大者pos2
            // pos1重新指定为xi
            } else {
                pos2 = pos1;
                pos1 = intervals[i][0];
                size++;
            }
        }
        return size;
    }
}

public class Main {
}
