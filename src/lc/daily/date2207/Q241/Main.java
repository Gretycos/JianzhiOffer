package lc.daily.date2207.Q241;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个由数字和运算符组成的字符串expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
 * 你可以 按任意顺序 返回答案。
 *
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10^4 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：expression = "2-1-1"
 * 输出：[0,2]
 * 解释：
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * 示例 2：
 *
 * 输入：expression = "2*3-4*5"
 * 输出：[-34,-14,-10,-10,10]
 * 解释：
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 *
 * 提示：
 *
 * 1 <= expression.length <= 20
 * expression 由数字和算符 '+'、'-' 和 '*' 组成。
 * 输入表达式中的所有整数值在范围 [0, 99]
 *
 *
 * */
class Solution {
    private String expression;
    private List<Integer> dfs(int start, int end){
        List<Integer> res = new ArrayList<>();
        for (int i = start; i <= end; i++){
            char c = expression.charAt(i);
            if (c >='0' && c <= '9') continue; // 数字
            // 运算符作为分割点，分割左右两部分
            List<Integer> left = dfs(start,i-1), right = dfs(i+1,end);
            // 左右两部分的运算结果枚举
            for (int a : left){
                for (int b : right){
                    int r = 0;
                    if (c == '+')
                        r = a + b;
                    else if (c == '-')
                        r = a - b;
                    else
                        r = a * b;
                    res.add(r);
                }
            }
        }
        // 结果集为空，说明区间内是数字本身，存入数字
        if (res.isEmpty()){
            int r = 0;
            // 字符串转数字
            for (int i = start; i <= end; i++){
                char c = expression.charAt(i);
                r = r * 10 + (c - '0');
            }
            res.add(r);
        }
        return res;
    }
    public List<Integer> diffWaysToCompute(String expression) {
        this.expression = expression;
        return dfs(0,expression.length()-1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "11";
        System.out.println(solution.diffWaysToCompute(expression));
    }
}
