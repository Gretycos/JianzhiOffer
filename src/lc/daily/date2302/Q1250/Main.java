package lc.daily.date2302.Q1250;

class Solution {
    public boolean isGoodArray(int[] nums) {
        int gcd = nums[0];
        for (int num : nums) {
            gcd = gcd(gcd, num);
            if (gcd == 1) return true;
        }
        return false;
    }
    private int gcd(int a, int b){
        return b == 0? a : gcd(b, a % b);
    }
}

public class Main {
}
