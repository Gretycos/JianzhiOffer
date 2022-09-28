package lc.daily.date2209.Interview17Q9;

/**
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
 * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。
 * 例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 * 输入: k = 5
 * 输出: 9
 *
 * */

class Solution {
    public int getKthMagicNumber(int k) {
        int[] res = new int[k];
        res[0] = 1;
        int t = 1;

        int p3 = 0, p5 = 0, p7 = 0;
        while (t < k){
            int min = Math.min(Math.min(res[p3] * 3, res[p5] * 5), res[p7] * 7);
            res[t++] = min;
            if (res[p3] * 3 == min) p3++;
            if (res[p5] * 5 == min) p5++;
            if (res[p7] * 7 == min) p7++;
        }
        return res[k-1];
    }
}


public class Main {
}
