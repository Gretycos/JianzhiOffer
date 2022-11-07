package lc.daily.date2211.Q816;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们有一些二维坐标，如"(1, 3)"或"(2, 0.5)"，然后我们移除所有逗号，小数点和空格，得到一个字符串S。
 * 返回所有可能的原始字符串到一个列表中。
 *
 * 原始的坐标表示法不会存在多余的零，所以不会出现类似于"00", "0.0", "0.00", "1.0", "001", "00.01"或一些其他更小的数来表示坐标。
 * 此外，一个小数点前至少存在一个数，所以也不会出现“.1”形式的数字。
 *
 * 最后返回的列表可以是任意顺序的。而且注意返回的两个数字中间（逗号之后）都有一个空格。
 *
 *
 *
 * 示例 1:
 * 输入: "(123)"
 * 输出: ["(1, 23)", "(12, 3)", "(1.2, 3)", "(1, 2.3)"]
 *
 *
 * 示例 2:
 * 输入: "(00011)"
 * 输出: ["(0.001, 1)", "(0, 0.011)"]
 * 解释:
 * 0.0, 00, 0001 或 00.01 是不被允许的。
 *
 *
 * 示例 3:
 * 输入: "(0123)"
 * 输出: ["(0, 123)", "(0, 12.3)", "(0, 1.23)", "(0.1, 23)", "(0.1, 2.3)", "(0.12, 3)"]
 *
 *
 * 示例 4:
 * 输入: "(100)"
 * 输出: [(10, 0)]
 * 解释:
 * 1.0 是不被允许的。
 *
 *
 * 提示:
 *
 * 4 <= S.length <= 12.
 * S[0] = "(", S[S.length - 1] = ")", 且字符串S中的其他元素都是数字。
 *
 * */

class Solution {
    public List<String> ambiguousCoordinates(String s) {
        List<String> res = new ArrayList<>();
        s = s.substring(1, s.length() - 1); // (123) -> 123
        for (int i = 1; i < s.length(); i++) {
            for (String x: getFloatNums(s.substring(0,i))){
                for (String y: getFloatNums(s.substring(i))){
                    StringBuilder sb = new StringBuilder();
                    res.add(sb.append('(').append(x).append(", ").append(y).append(')').toString());
                }
            }
        }
        return res;
    }

    private List<String> getFloatNums(String num){
        List<String> res = new ArrayList<>();
        String left, right;
        for (int i = 1; i <= num.length(); i++) {
            left = num.substring(0,i);
            right = num.substring(i);
            // 左半部分不是0，但是以0开头，跳过
            // 右半部分不为空，但是结尾有0，跳过
            if ((!left.equals("0") && left.charAt(0) == '0') ||
                    (!right.isEmpty() && right.charAt(right.length()-1) == '0'))
                continue;
            // 没有右半部分
            if (right.isEmpty()){
                res.add(left);
            }else{
                res.add(new StringBuilder().append(left).append('.').append(right).toString());
            }
        }
        return res;
    }
}
public class Main {
}
