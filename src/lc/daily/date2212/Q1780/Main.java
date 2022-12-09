package lc.daily.date2212.Q1780;

class Solution {
    public boolean checkPowersOfThree(int n) {
        while (n != 0){
            if (n % 3 == 2) return false;
            n /= 3;
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        int i = 0, N = 12;
        while (N / 3 > 0){
            i++;
            N /= 3;
        }
        System.out.println(i);
    }
}
