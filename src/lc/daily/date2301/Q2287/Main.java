package lc.daily.date2301.Q2287;

class Solution {
    public int rearrangeCharacters(String s, String target) {
        int[] sDict = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sDict[s.charAt(i) - 'a'] ++;
        }
        int[] targetDict = new int[26];
        for (int i = 0; i < target.length(); i++) {
            targetDict[target.charAt(i) - 'a'] ++;
        }
        int res = 100;
        for (int i = 0; i < 26; i++) {
            int countTarget = targetDict[i], countS = sDict[i];
            if (countTarget > 0){
                if (countS == 0) return 0; // 在s中不存在
                int copies = countS / countTarget;
                res = Math.min(res, copies);
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcba", target = "abc";
        System.out.println(solution.rearrangeCharacters(s,target));
    }
}
