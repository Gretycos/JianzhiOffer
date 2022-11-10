package lc.labuladong.DataStructure.array.Q1094;

import lc.labuladong.DataStructure.array.Difference;

/**
 * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
 *
 * 给定整数capacity和一个数组 trips,
 * trip[i] = [numPassengersi, fromi, toi]表示第 i 次旅行有numPassengersi乘客，
 * 接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
 *
 * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
 *
 *
 *
 * 示例 1：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 4
 * 输出：false
 *
 * 示例 2：
 *
 * 输入：trips = [[2,1,5],[3,3,7]], capacity = 5
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= trips.length <= 1000
 * trips[i].length == 3
 * 1 <= numPassengersi<= 100
 * 0 <= fromi< toi<= 1000
 * 1 <= capacity <= 10^5
 *
 * */
class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] nums = new int[1001];
        Difference df = new Difference(nums);

        for (int[] trip : trips) {
            int val = trip[0];
            int i = trip[1];
            int j = trip[2] - 1; // -1是因为乘客在trip[2]下车了
            df.increment(i,j,val);
        }
        int[] res = df.result();
        for (int num : res) {
            if (num > capacity){
                return false;
            }
        }
        return true;
    }
}
public class Main {
}
