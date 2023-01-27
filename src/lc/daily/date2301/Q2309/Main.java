package lc.daily.date2301.Q2309;

class Solution {
    public String greatestLetter(String s) {
        // 'z' ~ 'A' 共58个字符
        boolean[] flag = new boolean[58];
        for (int i = 0; i < s.length(); i++) {
            flag[s.charAt(i) - 'A'] = true;
        }
        // 这样leetcode里面会更快，但实际上是new了一个数组然后搬运字符串的字符进来，理论上应该更慢
//        for (char c : s.toCharArray()) {
//            flag[c - 'A'] = true;
//        }
        for (int i = 25; i >= 0; i--) {
            // 大写存在 小写也存在
            if (flag[i] && flag[i + 32]){
                return String.valueOf((char)('A' + i));
            }
        }
        return "";
    }
}

public class Main {
}
