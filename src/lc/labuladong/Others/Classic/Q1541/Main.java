package lc.labuladong.Others.Classic.Q1541;

class Solution {
    public int minInsertions(String s) {
        int res = 0;
        int need = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '('){
                // 遇到一个左括号，对右括号的需求量+2
                need += 2;
                if (need % 2 == 1){
                    // 插入一个右括号
                    res++;
                    // 对右括号的需求减一
                    need--;
                }
            } else {
                // 遇到一个右括号，对右括号的需求量-1
                need --;
                // 说明右括号太多了
                if (need == -1){
                    // 需要插入一个左括号
                    res ++;
                    // 同时，对右括号的需求变为 1
                    need = 1;
                }
            }
        }

        return res + need;
    }
}

public class Main {
}
