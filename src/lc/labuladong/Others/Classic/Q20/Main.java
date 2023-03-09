package lc.labuladong.Others.Classic.Q20;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> left = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '['){
                left.addLast(c);
            } else {
                if (!left.isEmpty() && leftOf(c) == left.getLast()){
                    left.removeLast();
                } else {
                    return false;
                }
            }
        }
        return left.isEmpty();
    }

    private char leftOf(char c){
        return switch (c){
            case '}' -> '{';
            case ')' -> '(';
            default -> '[';
        };
    }
}

public class Main {
}
