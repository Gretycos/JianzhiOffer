package lc.daily.date2208.Q640;

/**
 * 求解一个给定的方程，将x以字符串 "x=#value"的形式返回。该方程仅包含 '+' ， '-' 操作，变量x和其对应系数。
 *
 * 如果方程没有解，请返回"No solution"。如果方程有无限解，则返回 “Infinite solutions” 。
 *
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 *
 *
 *
 * 示例 1：
 *
 * 输入: equation = "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * 示例 2:
 *
 * 输入: equation = "x=x"
 * 输出: "Infinite solutions"
 * 示例 3:
 *
 * 输入: equation = "2x=x"
 * 输出: "x=0"
 *
 *
 * 提示:
 *
 * 3 <= equation.length <= 1000
 * equation只有一个'='.
 * equation方程由整数组成，其绝对值在[0, 100]范围内，不含前导零和变量 'x' 。
 *
 * */
class Solution {
    private String equation;
    private int n;
    private int p;
    private int coefficient, constant;
    private int sign = 1;
    private boolean findEqualSign;
    public String solveEquation(String equation) {
        this.equation = equation;
        coefficient = 0;
        constant = 0;
        n = equation.length();
        p = 0;
        findEqualSign = false;
        while (p < n){
            char c = equation.charAt(p);
            if (c =='x' || (c >= '0' && c <= '9')){
                sign = 1;
                parseValue();
            }else if (c == '+' || c == '-'){
                sign = c == '+' ? 1 : -1;
                p++;
                parseValue();
            }else if (c == '='){
                findEqualSign = true;
                p++;
            }
        }
        if (coefficient == 0){
            return constant == 0 ? "Infinite solutions" : "No solution";
        }

        return "x="+( - constant / coefficient);
    }

    private void parseValue(){
        int value = 0;
        while(p < n){
            char c = equation.charAt(p);
            if (c != 'x' && (c < '0' || c > '9')){
                break;
            }
            if (c == 'x'){
                if (value == 0){
                    // x
                    if (p == 0 || equation.charAt(p-1) != '0'){
                        coefficient += findEqualSign ?  - sign : sign;
                    }
                    // 0x 退出
                }else{
                    // ax, a > 1
                    coefficient += findEqualSign ? - sign * value : sign * value;
                }
                p++;
                return;
            }
            value = value * 10 + c - '0';
            p++;
        }
        constant += findEqualSign ? - sign * value : sign * value;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String equation = "0x=0";
        System.out.println(solution.solveEquation(equation));
    }
}
