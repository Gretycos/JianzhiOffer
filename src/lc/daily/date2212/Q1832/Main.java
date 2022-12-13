package lc.daily.date2212.Q1832;

class Solution {
    public boolean checkIfPangram(String sentence) {
        if (sentence.length() < 26) return false;
        int state = 0;
        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            state |= 1 << (c - 'a');
        }
        return state == (1 << 26) - 1;
    }
}


public class Main {
}
