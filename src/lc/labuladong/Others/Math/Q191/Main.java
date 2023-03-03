package lc.labuladong.Others.Math.Q191;

class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0){
            n = n & (n-1);
            res ++;
        }
        return res;
    }
}

public class Main {
}
