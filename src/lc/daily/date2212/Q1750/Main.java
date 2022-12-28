package lc.daily.date2212.Q1750;

class Solution {
    public int minimumLength(String s) {
        int n = s.length();
        int l = 0, r = n - 1;
        while (l < r && s.charAt(l) == s.charAt(r)){
            char c = s.charAt(l);
            while (l <= r && s.charAt(l) == c){
                l++;
            }
            while (l <= r && s.charAt(r) == c){
                r--;
            }
        }
        return r - l + 1;
    }
}

public class Main {
}
