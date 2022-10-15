package lc.hot100.Q76toQ100.Q394;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * 示例 2：
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * 示例 3：
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * 示例 4：
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 30
 * s由小写英文字母、数字和方括号'[]' 组成
 * s保证是一个有效的输入。
 * s中所有整数的取值范围为[1, 300]
 *
 * */


class Solution {
    private char[] arr;
    private int i;
    public String decodeString(String s) {
        arr = s.toCharArray();
        i = 0;
        return decode();
    }

    private String decode(){
        StringBuilder sb = new StringBuilder();
        while (i < arr.length){
            if (arr[i] >= '0' && arr[i] <= '9'){
                int times = 0;
                while (arr[i] >= '0' && arr[i] <= '9'){
                    times = times * 10 + arr[i] - '0';
                    i++;
                }
                i++; // 跳过[
                sb.append(decode().repeat(times));
                i++; // 跳过]
            }else if (arr[i] >='a' && arr[i] <='z'){
                sb.append(arr[i++]);
            }else if (arr[i] == ']'){
                break;
            }
        }
        return sb.toString();
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.decodeString("3[z]2[2[y]pq4[2[jk]e1[f]]]ef"));
    }
}