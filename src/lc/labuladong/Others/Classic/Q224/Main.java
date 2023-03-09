package lc.labuladong.Others.Classic.Q224;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    private int i = 0;

    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int num = 0;
        char sign = '+';

        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean isDigit = Character.isDigit(c);
            // 遇到左括号开始递归计算 num
            if (c == '(') {
                i++;
                num = calculate(s);
            }

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

            if (c == ')') break;
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.removeLast();
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = " 2-1 + 2  * 7  / 2 + 5";
        System.out.println(solution.calculate(s));
    }
}
