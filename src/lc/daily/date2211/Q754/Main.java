package lc.daily.date2211.Q754;

/**
 * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
 *
 * 你可以做一些数量的移动 numMoves :
 *
 * 每次你可以选择向左或向右移动。
 * 第 i次移动（从 i == 1开始，到i == numMoves ），在选择的方向上走 i步。
 * 给定整数target ，返回 到达目标所需的 最小移动次数(即最小 numMoves )。
 *
 *
 *
 * 示例 1:
 *
 * 输入: target = 2
 * 输出: 3
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 -1 。
 * 第三次移动，从 -1 到 2 。
 *
 * 示例 2:
 *
 * 输入: target = 3
 * 输出: 2
 * 解释:
 * 第一次移动，从 0 到 1 。
 * 第二次移动，从 1 到 3 。
 *
 *
 * 提示:
 *
 * -10^9 <= target <= 10^9
 * target != 0
 *
 * */
class Solution {
    public int reachNumber(int target) {
        target = Math.abs(target); // 正负情况是对称的，所以只用考虑正向
        int sum = 0;
        int step = 0;
        // 如果sum < target就一直走
        // 如果sum == target，直接返回结果
        // 如果sum > target，令diff = sum - target
        //      1. 如果diff是偶数，说明可以翻转前面的一个数a为负，使得sum减少2a，来达到target
        //      2. 如果diff是奇数，需要继续走【
        //      由于反向操作只能将sum减少偶数，无法处理相距奇数的情况，必须多走一两步，将相距变为偶数，才能处理。】
        //          如果下一步是奇数，则新diff是偶数，回到1.
        //          如果下一步是偶数，则新diff是奇数，那需要再走一步使得diff变成偶数，回到1.
        while (sum < target || ((sum - target) & 1) != 0){
            sum += ++step;
        }
        return step;
    }
}

public class Main {
}
