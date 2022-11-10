package lc.labuladong.DataStructure.array.Q370;

import lc.labuladong.DataStructure.array.Difference;

/**
 * 假设你有一个长度为n的数组，初始情况下所有的数字均为0，你将会被给出k个更新的操作。
 * 其中，每个操作会被表示为一个三元组：[startIndex,endIndex,inc]，
 * 你需要将子数组A[startIndex..endIndex]（包括startIndex和endIndex）增加inc
 * 请你返回k次操作后的数组。
 *
 * 示例：
 * 输入：length=5,updates=[[1,3,2],[2,4,31,[0,2,-21]
 * 输出：[-2,0,3,5,3」
 *
 * 解释：
 * 初始状态：
 * 10, 0,0,0, 01
 * 进行了操作[1,3,2〕后的状态：
 * [0,2,2,2,01
 * 进行了操作[2,4,3]后的状态：
 * [0,2,5,5,3]
 * 进行了操作[0,2,-2〕后的状态：
 * [-2,0,3,5,3]
 *
 * */


class Solution{
    int[] getModifiedArray(int length, int[][] updates) {
        // nums 初始化为全 0
        int[] nums = new int[length];
        // 构造差分解法
        Difference df = new Difference(nums);

        for (int[] update : updates) {
            int i = update[0];
            int j = update[1];
            int val = update[2];
            df.increment(i, j, val);
        }

        return df.result();
    }
}
public class Main {
}
