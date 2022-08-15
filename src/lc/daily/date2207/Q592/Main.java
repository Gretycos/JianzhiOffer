package lc.daily.date2207.Q592;


/**
 * 给定一个表示分数加减运算的字符串expression，你需要返回一个字符串形式的计算结果。
 *
 * 这个结果应该是不可约分的分数，即最简分数。如果最终结果是一个整数，例如2，你需要将它转换成分数形式，其分母为1。
 * 所以在上述例子中, 2应该被转换为2/1。
 *
 *
 *
 * 示例1:
 *
 * 输入:expression= "-1/2+1/2"
 * 输出: "0/1"
 *
 * 示例 2:
 *
 * 输入:expression= "-1/2+1/2+1/3"
 * 输出: "1/3"
 * 示例 3:
 *
 * 输入:expression= "1/3-1/2"
 * 输出: "-1/6"
 *
 *
 * 提示:
 *
 * 输入和输出字符串只包含'0' 到'9'的数字，以及'/', '+' 和'-'。
 * 输入和输出分数格式均为±分子/分母。如果输入的第一个分数或者输出的分数是正数，则'+'会被省略掉。
 * 输入只包含合法的最简分数，每个分数的分子与分母的范围是[1,10]。如果分母是1，意味着这个分数实际上是一个整数。
 * 输入的分数个数范围是 [1,10]。
 * 最终结果的分子与分母保证是 32 位整数范围内的有效整数。
 *
 * */


class Solution {
    private String expression;
    // 字符串长度
    private int n;
    // 下标
    private int i;
    // 分子 分母 符号
    private int numerator, denominator, sign;
    public String fractionAddition(String expression) {
        StringBuilder res = new StringBuilder();
        this.expression = expression;
        n = expression.length();
        i = 0;
        numerator = 0;
        denominator = 1;
        sign = 1;

        while(i < n){
            char c = expression.charAt(i);
            if (c == '-'){
                sign = -1;
                i ++;
                continue;
            }
            if (c == '+'){
                sign = 1;
                i ++;
                continue;
            }
            if (c >= '0' && c <='9'){
                // 分子
                int numer = parseInt();
                // 跳过'/'
                i ++;
                // 分母
                int denom = parseInt();
                calculate(numer,denom);
            }
        }
        res.append(numerator);
        res.append('/');
        res.append(denominator);
        return res.toString();
    }
    private int parseInt(){
        int res = 0;
        while(i < n && expression.charAt(i) >='0' && expression.charAt(i) <= '9'){
            res = res * 10 + expression.charAt(i) - '0';
            i++;
        }
        return res;
    }
    private void calculate(int numer, int denom){
        // 分母相同，分子直接相加减
        if ( denom == denominator){
            numerator += sign * numer;
        // 分母不同，分子交叉相乘相加减
        // 分母相乘
        } else {
            numerator =  numerator * denom + sign * numer * denominator;
            denominator *= denom;
        }
        // 分子为0则把分母设为1，然后结束
        if (numerator == 0){
            denominator = 1;
            return;
        }
        // 分子不为0则考虑约分
        int gcd = gcd(Math.abs(numerator),denominator);
        numerator /= gcd;
        denominator /= gcd;
    }

    /**
     * 求最大公约数：辗转相除法*/
    private int gcd(int num1, int num2){
        while(num1 % num2 != 0){
            int t = num1 % num2;
            num1 = num2;
            num2 = t;
        }
        return num2;
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String expression = "-1/2+1/2+1/32-4/64";
        System.out.println(solution.fractionAddition(expression));
    }
}
