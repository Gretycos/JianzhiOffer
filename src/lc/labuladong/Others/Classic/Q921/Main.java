package lc.labuladong.Others.Classic.Q921;

class Solution {
    public int minAddToMakeValid(String s) {
        // res 记录左括号插入次数
        int res = 0;
        // need 变量记录右括号的需求量
        int need = 0;

        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if (c == '('){
                // 对右括号的需求+1
                need++;
            } else {
                // 对右括号的需求-1
                need --;
                if (need == -1){
                    need = 0;
                    // 插入一个左括号
                    res++;
                }
            }
        }

        // for 循环结束后若 need 不为 0
        // 那么就意味着右括号还不够，需要插入。
        return res + need;
    }
}

public class Main {
}
