package lc.daily.date2211.Q878;


class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        // 令f(x)为<=x的神奇数字的数量 -> <=x的被a整除或被b整除的个数
        // 被a整除的数 = x / a, 被b整除的数 = x / b
        // 被a、b同时整除的数 = x / lcm(a,b)
        // f(x) = x / a + x / b - x / lcm(a,b)
        long left = Math.min(a,b), right = (long) n * Math.min(a,b);
        int c = lcm(a,b);
        while (left <= right){
            long mid = left + (right - left) / 2;
            long nTested = mid / a + mid / b - mid / c;
            if (nTested == n){
                right = mid - 1;
            }else if (nTested < n){
                left = mid + 1;
            }else if (nTested > n){
                right = mid - 1;
            }
        }
        int MOD = (int) 1e9 + 7;
        return (int)(left % MOD);
    }

    private int lcm(int a, int b){
        return a * b / gcd(a,b);
    }

    private int gcd(int a, int b){
        int c = a % b;
        while(c != 0){
            a = b;
            b = c;
            c = a % b;
        }
        return b;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.nthMagicalNumber(100000000,2,3));
    }
}
