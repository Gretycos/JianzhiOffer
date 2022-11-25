package lc.daily.date2211.Q809;

class Solution {
    public int expressiveWords(String s, String[] words) {
        int res = 0;
        for (String word : words) {
            int i = 0, p = 0; // 双指针
            boolean canBeExpanded = true;
            while (i < word.length() && p < s.length()){
                if (word.charAt(i) != s.charAt(p)){
                    canBeExpanded = false;
                    break;
                }
                // 当前字符
                char c = s.charAt(p);
                // 计算word中c的数量
                int countW = 0;
                while (i < word.length() && word.charAt(i) == c){
                    countW++;
                    i++;
                }
                // 计算s中c的数量
                int countS = 0;
                while (p < s.length() && s.charAt(p) == c){
                    countS++;
                    p++;
                }
                // 如果s中c的数量 < word中c的数量，无法扩展
                // 如果s中c的数量 < 3，但是与word中c的数量不相同，无法扩张
                if (countS < countW || (countS != countW && countS < 3)){
                    canBeExpanded = false;
                    break;
                }
            }
            // 如果字符串都遍历完，而且可扩展
            if (i == word.length() && p == s.length() && canBeExpanded){
                res ++;
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] words = {"abc"};
        String s = "abcd";
        System.out.println(solution.expressiveWords(s,words));
    }
}
