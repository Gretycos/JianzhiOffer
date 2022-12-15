package lc.daily.date2212.Q1945;

class Solution {
    public int getLucky(String s, int k) {
        int lucky = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'a' + 1;
            while (num > 0){
                lucky += num % 10;
                num /= 10;
            }
        }
        for (int i = 1; i < k; i++) {
            int num = lucky;
            lucky = 0;
            while (num > 0){
                lucky += num % 10;
                num /= 10;
            }
        }
        return lucky;
    }
}

public class Main {
}
