package lc.labuladong.DataStructure.array.Q5;

// 旧题
class Solution {
    public String longestPalindrome(String s) {
        int l = 0, r = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] s1 = palindrome(s,i,i);
            int[] s2 = palindrome(s,i,i+1);
            int len1 = s1[1] - s1[0] + 1,
                len2 = s2[1] - s2[0] + 1;
            if (Math.max(len1,len2) > r - l + 1){
                l = len1 > len2 ? s1[0] : s2[0];
                r = len1 > len2 ? s1[1] : s2[1];
            }
        }
        return s.substring(l,r+1);
    }

    // 寻找以s[l]和s[r]为中心的最长回文串的下标区间
    private int[] palindrome(String s, int l, int r){
        // 中心扩展
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
            l--;
            r++;
        }
        return new int[]{l+1,r-1};
    }
}

public class Main {
}
