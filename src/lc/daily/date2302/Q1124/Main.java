package lc.daily.date2302.Q1124;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int longestWPI(int[] hours) {
        int n = hours.length;
        Map<Integer,Integer> map = new HashMap<>();
        int preSum = 0; // 前缀和
        int res = 0;
        for (int i = 0; i < n; i++) {
            preSum += hours[i] > 8? 1 : -1;
            if (preSum > 0){ // 说明从开始到位置i存在所求序列
                res = Math.max(res, i + 1);
            } else {
                // 如果某一个位置的前缀和 preSum 不大于 0 ，我们只需要往前找到前缀和等于 preSum-1 的即可。
                // 如果当前下标对应的 preSum 不大于 0 ，说明数组从最开始到当前下标构成的子数组不是「表现良好时间段」。
                // 我们需要 减去最前面的一段子数组，让剩下的子数组构成「表现良好时间段」。
                // 让子数组和为1，得到的是最长的
                int j = map.getOrDefault(preSum - 1, -1);
                if (j != -1){
                    res = Math.max(res, i - j);
                }
            }
            // 记录第一次出现的位置，因为这样最长
            map.putIfAbsent(preSum, i);
        }
        return res;
    }
}

public class Main {
}
