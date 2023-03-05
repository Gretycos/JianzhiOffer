package lc.labuladong.Others.Math.Q793;

class Solution {
    private final long MAX = 4000000019L;
    public int preimageSizeFZF(int k) {
        return (int)(right_bound(k) - left_bound(k) + 1);
    }

    /* 搜索 trailingZeroes(n) == K 的左侧边界 */
    private long left_bound(int target) {
        long lo = 0, hi = MAX;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid - 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }

    /* 搜索 trailingZeroes(n) == K 的右侧边界 */
    long right_bound(int target) {
        long lo = 0, hi = MAX;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return hi;
    }

    // 逻辑不变，数据类型全部改成 long
    private long trailingZeroes(long n) {
        long res = 0;
        for (long d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }
}

public class Main {
}
