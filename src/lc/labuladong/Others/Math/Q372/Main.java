package lc.labuladong.Others.Math.Q372;

class Solution {
    private final int MOD = 1337;
    public int superPow(int a, int[] b) {
        return superPow(a,b, b.length-1);
    }

    private int superPow(int a, int[] b, int p){
        // 递归的 base case
        if (p == -1){
            return 1;
        }
        // 取出最后一个数
        int last = b[p--];
        // 将原问题化简，缩小规模递归求解
        int part1 = myPow(a, last);
        int part2 = myPow(superPow(a, b, p), 10);
        // 每次乘法都要求模
        return (part1 * part2) % MOD;
    }

    // 求a的k次幂然后与MOD取模
    private int myPow(int a, int k){
//        a %= MOD;
//        int res = 1;
//        for (int i = 0; i < k; i++) {
//            // 这里有乘法，是潜在的溢出点
//            res *= a;
//            // 对乘法结果求模
//            res %= MOD;
//        }
//        return res;

        if (k == 0) return 1;
        a %= MOD;

        if (k % 2 == 1){
            // k 是奇数
            return (a * myPow(a, k-1)) % MOD;
        } else {
            // k 是偶数
            int sub = myPow(a, k / 2);
            return (sub * sub) % MOD;
        }
    }
}

public class Main {
}
