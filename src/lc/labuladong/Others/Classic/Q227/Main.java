package lc.labuladong.Others.Classic.Q227;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution{
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int num = 0;
        char sign = '+';

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isDigit = Character.isDigit(c);

            if (isDigit) {
                num = 10 * num + (c - '0');
            }

            // 如果不是数字，就是遇到了下一个符号，
            // 之前的数字和符号就要存进栈中
            if ((!isDigit && c != ' ') || i == s.length() - 1) {
                switch (sign) {
                    case '+' -> stack.addLast(num);
                    case '-' -> stack.addLast(-num);
                    // 乘除只要拿出前一个数字做对应运算即可
                    case '*' -> stack.addLast(stack.removeLast() * num);
                    case '/' -> stack.addLast(stack.removeLast() / num);
                }
                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.removeLast();
        }
        return res;
    }
}

public class Main {
}
