package lc.daily.date2211.Q1620;

import java.util.Arrays;

/**
 * 给你一个数组 towers和一个整数 radius 。
 *
 * 数组 towers 中包含一些网络信号塔，其中towers[i] = [xi, yi, qi]表示第i个网络信号塔的坐标是(xi, yi)且信号强度参数为qi。
 * 所有坐标都是在 X-Y 坐标系内的整数坐标。两个坐标之间的距离用 欧几里得距离计算。
 *
 * 整数radius表示一个塔 能到达的 最远距离。如果一个坐标跟塔的距离在 radius以内，那么该塔的信号可以到达该坐标。
 * 在这个范围以外信号会很微弱，所以 radius以外的距离该塔是 不能到达的。
 *
 * 如果第 i个塔能到达 (x, y)，那么该塔在此处的信号为⌊qi / (1 + d)⌋，其中d是塔跟此坐标的距离。
 * 一个坐标的 信号强度 是所有 能到达该坐标的塔的信号强度之和。
 *
 * 请你返回数组 [cx, cy] ，表示 信号强度 最大的 整数 坐标点(cx, cy) 。如果有多个坐标网络信号一样大，请你返回字典序最小的 非负 坐标。
 *
 * 注意：
 *
 * 坐标(x1, y1)字典序比另一个坐标(x2, y2) 小，需满足以下条件之一：
 * 要么x1 < x2，
 * 要么x1 == x2 且y1 < y2。
 * ⌊val⌋表示小于等于val的最大整数（向下取整函数）。
 *
 *
 * 示例 1：
 *
 *
 * 输入：towers = [[1,2,5],[2,1,7],[3,1,9]], radius = 2
 * 输出：[2,1]
 * 解释：
 * 坐标 (2, 1) 信号强度之和为 13
 * - 塔 (2, 1) 强度参数为 7 ，在该点强度为 ⌊7 / (1 + sqrt(0)⌋ = ⌊7⌋ = 7
 * - 塔 (1, 2) 强度参数为 5 ，在该点强度为 ⌊5 / (1 + sqrt(2)⌋ = ⌊2.07⌋ = 2
 * - 塔 (3, 1) 强度参数为 9 ，在该点强度为 ⌊9 / (1 + sqrt(1)⌋ = ⌊4.5⌋ = 4
 * 没有别的坐标有更大的信号强度。
 *
 *
 * 示例 2：
 *
 * 输入：towers = [[23,11,21]], radius = 9
 * 输出：[23,11]
 * 解释：由于仅存在一座信号塔，所以塔的位置信号强度最大。
 *
 *
 * 示例 3：
 *
 * 输入：towers = [[1,2,13],[2,1,7],[0,1,9]], radius = 2
 * 输出：[1,2]
 * 解释：坐标 (1, 2) 的信号强度最大。
 *
 *
 * 提示：
 *
 * 1 <= towers.length <= 50
 * towers[i].length == 3
 * 0 <= xi, yi, qi <= 50
 * 1 <= radius <= 50
 *
 * */

class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        // 最大值只能出现在塔围成的矩形内
        int X = Integer.MIN_VALUE, Y = Integer.MIN_VALUE;
        for (int[] tower : towers) {
            int x = tower[0], y = tower[1];
            if (x > X) X = x;
            if (y > Y) Y = y;
        }
        int bestQuality = 0;
        int[] res = {0,0};
        // 从0,0开始，天然的字典序
        for (int x = 0; x <= X; x++) {
            for (int y = 0; y <= Y; y++) {
                int quality = 0;
                for (int[] tower : towers) {
                    int d = (x - tower[0]) * (x - tower[0]) + (y - tower[1]) * (y - tower[1]);
                    if (d <= radius * radius){
                        quality += (int)(tower[2] / (1 + Math.sqrt(d))); // 题目要求取下整
                    }
                }
                if (quality > bestQuality){
                    bestQuality = quality;
                    res[0] = x;
                    res[1] = y;
                }
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] towers = {
                {30,34,31},
                {10,44,24},
                {14,28,23},
                {50,38,1},
                {40,13,6},
                {16,27,9},
                {2,22,23},
                {1,6,41},
                {34,22,40},
                {40,10,11},

        };
        System.out.println(Arrays.toString(solution.bestCoordinate(towers, 20)));
    }
}
