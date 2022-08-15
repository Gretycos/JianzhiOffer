package lc.daily.date2207.Q736;

import java.util.*;

/**
 * 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 *
 * 表达式语法如下所示:
 *
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 *
 * let 表达式采用"(let v1 e1 v2 e2 ... vn en expr)" 的形式，
 * 其中let 总是以字符串"let"来表示，接下来会跟随一对或多对交替的变量和表达式，
 * 也就是说，第一个变量v1被分配为表达式e1的值，第二个变量v2被分配为表达式e2的值，依次类推；
 * 最终 let 表达式的值为expr表达式的值。
 *
 * add 表达式表示为"(add e1 e2)" ，
 * 其中add 总是以字符串"add" 来表示，该表达式总是包含两个表达式 e1、e2 ，最终结果是e1 表达式的值与e2表达式的值之和。
 *
 * mult 表达式表示为"(mult e1 e2)"，
 * 其中mult 总是以字符串 "mult" 表示，该表达式总是包含两个表达式 e1、e2，最终结果是e1 表达式的值与e2表达式的值之积。
 *
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。
 * 为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），
 * 然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 *
 * 示例 1：
 *
 * 输入：expression = "(let x 2 (mult x (let x 3 y 4 (add x y))))"
 * 输出：14
 * 解释：
 * 计算表达式 (add x y), 在检查变量 x 值时，
 * 在变量的上下文中由最内层作用域依次向外检查。
 * 首先找到 x = 3, 所以此处的 x 值是 3 。
 * 示例 2：
 *
 * 输入：expression = "(let x 3 x 2 x)"
 * 输出：2
 * 解释：let 语句中的赋值运算按顺序处理即可。
 * 示例 3：
 *
 * 输入：expression = "(let x 1 y 2 x (add x y) (add x y))"
 * 输出：5
 * 解释：
 * 第一个 (add x y) 计算结果是 3，并且将此值赋给了 x 。
 * 第二个 (add x y) 计算结果是 3 + 2 = 5 。
 *
 * 提示：
 *
 * 1 <= expression.length <= 2000
 * exprssion 中不含前导和尾随空格
 * expressoin 中的不同部分（token）之间用单个空格进行分隔
 * 答案和所有中间计算结果都符合 32-bit 整数范围
 * 测试用例中的表达式均为合法的且最终结果为整数
 *
 * */


class Solution {
    // 变量映射表
    // deque用作栈
    private Map<String, Deque<Integer>> map = new HashMap<>();
    private int start = 0;
    private String expression;

    private int parseInt(){
        int num = 0, sign = 1;
        // 处理符号
        if (expression.charAt(start) == '-'){
            sign = -1;
            start++;
        }
        while(start < expression.length() && Character.isDigit(expression.charAt(start))){
            num = num * 10 + (expression.charAt(start) - '0');
            start++;
        }
        return sign * num;
    }
    private String parseVar(){
        StringBuilder sb = new StringBuilder();
        while(start < expression.length()
                && expression.charAt(start) != ' ' && expression.charAt(start) != ')'){
            sb.append(expression.charAt(start++));
        }
        return sb.toString();
    }
    private int innerEvaluate(){
        char c = expression.charAt(start);
        // 非表达式，可能是 整数、变量
        if (c != '('){
            // 变量
            if (Character.isLowerCase(c)){
                String var = parseVar();
                return map.get(var).peek();
            // 整数
            }else{
                return parseInt();
            }
        }
        // 是表达式
        int res;
        start++; // 去 '('

        // let表达式
        if (expression.charAt(start) == 'l'){
            start += 4; // 移除"let "
            List<String> vars = new ArrayList<>(); // 当前作用域的变量
            while(true){
                // 不是变量名
                if (!Character.isLowerCase(expression.charAt(start))){
                    res = innerEvaluate();
                    break;
                }
                // 是变量名
                String var = parseVar();
                // 变量名后面是')' 说明表达式结束，返回变量结果
                if (expression.charAt(start) == ')'){
                    res = map.get(var).peek();
                    break;
                }
                // 还没结束
                vars.add(var);
                // 去空格
                start++;
                // 获得变量值
                int val = innerEvaluate();
                // 把变量和值存入map
                map.putIfAbsent(var, new ArrayDeque<>());
                map.get(var).push(val); // 推入顶部
                // 去空格
                start++;
            }
            // 清除当前作用域的变量
            for (String var: vars){
                map.get(var).pop();
            }
        }
        // add表达式
        else if (expression.charAt(start) == 'a'){
            start += 4;
            int val1 = innerEvaluate();
            start ++;
            int val2 = innerEvaluate();
            res = val1 + val2;
        }
        // mult表达式
        else{
            start += 5;
            int val1 = innerEvaluate();
            start++;
            int val2 = innerEvaluate();
            res = val1 * val2;
        }
        start++; // 移除')'
        return res;
    }

    public int evaluate(String expression) {
        this.expression = expression;
        return innerEvaluate();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "(let x 1 y 2 x (add x y) (add x y))";
        System.out.println(solution.evaluate(expression));
    }
}
