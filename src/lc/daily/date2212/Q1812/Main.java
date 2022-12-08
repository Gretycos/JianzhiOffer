package lc.daily.date2212.Q1812;

class Solution {
    public boolean squareIsWhite(String coordinates) {
        return ((coordinates.charAt(0) - 'a' + coordinates.charAt(1)) & 1) == 0;
    }
}

public class Main {
}
