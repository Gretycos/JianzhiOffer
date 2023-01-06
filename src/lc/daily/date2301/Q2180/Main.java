package lc.daily.date2301.Q2180;

class Solution {
    public int countEven(int num) {
        int count = num / 10 * 5 - 1;
        int preSum = 0;
        for (int x = num / 10; x > 0; x /= 10) {
            preSum += x % 10;
        }
        count += ((preSum & 1) == 0) ? lastEven(num % 10) : lastOdd(num % 10);
        return count;
    }

    private int lastOdd(int num){
        int count = 0;
        for (int i = 0; i <= num; i++) {
            if ((i & 1) != 0) count++;
        }
        return count;
    }

    private int lastEven(int num){
        int count = 0;
        for (int i = 0; i <= num; i++) {
            if ((i & 1) == 0) count++;
        }
        return count;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countEven(100));
    }
}
