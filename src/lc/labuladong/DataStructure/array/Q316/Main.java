package lc.labuladong.DataStructure.array.Q316;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String removeDuplicateLetters(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        boolean[] isInStack = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            counts[c - 'a']--;
            // 字符已有，跳过
            if (isInStack[c-'a']) continue;
            while (!stack.isEmpty() && stack.getLast() > c){
                // 后面没有该字符了，不能移除
                if (counts[stack.getLast() - 'a'] == 0) break;
                isInStack[stack.removeLast() - 'a'] = false;
            }
            stack.addLast(c);
            isInStack[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.removeLast());
        }
        return sb.reverse().toString();
    }
}

public class Main {
}
