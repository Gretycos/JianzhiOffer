package lc.daily.date2302.Q2325;

import java.util.Arrays;

class Solution {
    public String decodeMessage(String key, String message) {
        int[] map = new int[26]; // key -> 字母表
        Arrays.fill(map, -1);
        for (int i = 0, idx = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (c == ' ') continue;
            if (map[c-'a'] == -1){
                map[c-'a'] = idx++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c != ' ') {
                c = (char)(map[c-'a']+'a');
            }
            sb.append(c);
        }
        return sb.toString();
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String key = "the quick brown fox jumps over the lazy dog";
        String message = "vkbs bs t suepuv";
        System.out.println(solution.decodeMessage(key, message));
    }
}
