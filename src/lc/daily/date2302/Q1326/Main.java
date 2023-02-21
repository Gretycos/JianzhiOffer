package lc.daily.date2302.Q1326;

class Solution {
    public int minTaps(int n, int[] ranges) {
        // 定义：rightMax[i]是以i为起点，往右水龙头能到达的最大点
        int[] rightMax = new int[n + 1]; // n + 1个水龙头
        for (int i = 0; i < n + 1; i++) {
            int l = Math.max(0, i - ranges[i]), r = i + ranges[i];
            rightMax[l] = Math.max(rightMax[l], r);
        }
        int res = 0;
        int max = 0; // 当前能覆盖的最右端点
        int pre = 0; // 上一个水龙头能覆盖的最右端点

        for (int i = 0; i < n; i++) {
            max = Math.max(max, rightMax[i]);
            if (max <= i){ // 无法从i出发到达下一个点
                return -1;
            }
            if (pre == i){ // 需要一个新区间进行覆盖
                res++;
                pre = max;
            }
        }

        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 5;
        int[] ranges = {3,4,1,1,0,0};
        System.out.println(solution.minTaps(n, ranges));
    }
}
