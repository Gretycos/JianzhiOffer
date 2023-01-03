package lc.daily.date2301.Q2042;

class Solution {
    public boolean areNumbersAscending(String s) {
        String[] tokens = s.split(" ");
        int lastNum = -1;
        for (String token : tokens) {
            if (token.charAt(0) >= 'a') continue;
            int curNum = 0;
            for (int i = 0; i < token.length(); i++) {
                curNum = curNum * 10 + (token.charAt(i) - '0');
            }
            if (curNum <= lastNum) return false;
            lastNum = curNum;
        }
        return true;
    }
}

public class Main {
}
