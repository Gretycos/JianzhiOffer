package lc.labuladong.DynamicProgress.greedy.Q1024;

import java.util.Arrays;

class Solution {
    public int videoStitching(int[][] clips, int time) {
        if (time == 0) return 0;
        Arrays.sort(clips,
                (v1,v2) -> v1[0] - v2[0] > 0 ? v1[0] - v2[0] : v2[1] - v1[1]);

        // 记录选择的视频数
        int res = 0;

        int curEnd = 0, nextEnd = 0;
        int i = 0, n = clips.length;
        while (i < n && clips[i][0] <= curEnd){
            // 在第res个视频的区间内贪心选择下一个视频
            while (i < n && clips[i][0] <= curEnd){
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }
            // 找到下一个视频，更新curEnd
            res ++;
            curEnd = nextEnd;
            if (curEnd >= time){
                // 已经可以拼出区间[0,time]
                return res;
            }
        }
        // 无法拼出区间[0,time]
        return -1;
    }
}

public class Main {
}
