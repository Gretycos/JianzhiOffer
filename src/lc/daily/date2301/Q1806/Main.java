package lc.daily.date2301.Q1806;

class Solution {
    public int reinitializePermutation(int n) {
        return dp(n,n/2);
    }
    // 定义：dp(n,i)为n个数第i个下标回到i的最少次数
    private int dp(int n, int i){
        if (i == 1) return 1;
        int count = isE2(i);
        if (count != -1){
            return count;
        } else if (i % 2 == 0){
            return 1 + dp(n, i / 2);
        }else{
            return 1 + dp(n, n / 2 + (i - 1) / 2);
        }
    }

    // 是否是2的指数
    int isE2(int i){
        int count = 1;
        while (i % 2 == 0){
            i /= 2;
            count ++;
        }
        if (i == 1) return count;

        return -1;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reinitializePermutation(998));
    }
}
