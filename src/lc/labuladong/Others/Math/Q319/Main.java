package lc.labuladong.Others.Math.Q319;

class Solution {
    public int bulbSwitch(int n) {
        // 平方数的不同因数不是成对的，所以只会被按奇数次，最后一定是亮的
        // 问题转化成小于等于n有多少个平方数
        return (int) Math.sqrt(n);
    }
}

public class Main {
}
