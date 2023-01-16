package lc.daily.date2301.Q1813;

class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        // 短的句子作为s1，长的句子作为s2
        if (sentence1.length() > sentence2.length()){
            return areSentencesSimilar(sentence2, sentence1);
        }
        // 拆分成字符串数组
        String[] source = sentence1.split(" ");
        String[] target = sentence2.split(" ");
        // 双指针
        int l1 = 0, r1 = source.length - 1;
        int l2 = 0, r2 = target.length - 1;
        // 如果其中一个已经走完了，说明完成了两端匹配
        while (l1 <= r1 && l2 <= r2) {
            boolean leftMatched = source[l1].equals(target[l2]);
            boolean rightMatched = source[r1].equals(target[r2]);
            if (leftMatched){
                l1 ++;
                l2 ++;
            }
            if (rightMatched){
                r1 --;
                r2 --;
            }
            // 如果出现两端都不匹配的现象，说明句子不相似
            if (!leftMatched && !rightMatched){
                return false;
            }
        }
        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s1 = "My name Haley";
        String s2 = "My name is Bob Haley";
        System.out.println(solution.areSentencesSimilar(s1,s2));
    }
}
