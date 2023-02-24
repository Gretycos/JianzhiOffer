package lc.labuladong.DynamicProgress.greedy.Q253;

import java.util.Arrays;

class Solution{
    public int minMeetingRooms(int[][] meetings){
        int n = meetings.length;
        int[] begin = new int[n];
        int[] end = new int[n];
        for (int i = 0; i < n; i++) {
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);

        // 扫描线的计数器
        int count = 0;
        // 双指针技巧
        int res = 0, i = 0, j = 0;
        while (i < n && j < n){
            if (begin[i] < end[j]){
                // 扫描到红点
                i++;
                count++;
            } else {
                // 扫描到绿点
                j++;
                count--;
            }
            // 记录最大值
            res = Math.max(res, count);
        }
        return res;
    }
}
public class Main {
}
