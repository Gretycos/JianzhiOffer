package lc.daily.date2302.Q1238;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> circularPermutation(int n, int start) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; ++i) {
            // 格雷码构造
            // g(i) = i ^ (i / 2) ---- 以0开头
            // g(i) = i ^ (i / 2) ^ start ---- 以start开头
            res.add(i ^ (i >> 1) ^ start);
        }
        return res;
    }
}

public class Main {
}
