package lc.labuladong.Others.Math.Q231;

class Solution {
    public boolean isPowerOfTwo(int n) {
        return n >= 0 && (n & (n - 1)) == 0;
    }
}

public class Main {
}
