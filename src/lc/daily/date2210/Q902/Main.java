package lc.daily.date2210.Q902;

/**
 * 给定一个按非递减顺序排列的数字数组digits。你可以用任意次数digits[i]来写的数字。
 * 例如，如果digits = ['1','3','5']，我们可以写数字，如'13','551', 和'1351315'。
 *
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数。
 *
 *
 *
 * 示例 1：
 *
 * 输入：digits = ["1","3","5","7"], n = 100
 * 输出：20
 * 解释：
 * 可写出的 20 个数字是：
 * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
 *
 *
 * 示例 2：
 *
 * 输入：digits = ["1","4","9"], n = 1000000000
 * 输出：29523
 * 解释：
 * 我们可以写 3 个一位数字，9 个两位数字，27 个三位数字，
 * 81 个四位数字，243 个五位数字，729 个六位数字，
 * 2187 个七位数字，6561 个八位数字和 19683 个九位数字。
 * 总共，可以使用D中的数字写出 29523 个整数。
 *
 *
 * 示例 3:
 *
 * 输入：digits = ["7"], n = 8
 * 输出：1
 *
 *
 * 提示：
 *
 * 1 <= digits.length <= 9
 * digits[i].length == 1
 * digits[i]是从'1'到'9' 的数
 * digits中的所有值都 不同
 * digits按非递减顺序排列
 * 1 <= n <= 10^9
 *
 * */

class Solution {
    private int[] d;
    private int dLen;
    private String s;
    private int sLen;

    // Time: O(klogn)
    public int atMostNGivenDigitSet(String[] digits, int n) {
        dLen = digits.length;
        d = new int[dLen];
        // 把String[] -> int[]
        for (int i = 0; i < dLen; i++) {
            d[i] = Integer.parseInt(digits[i]);
        }
        // 把int n -> String s
        s = String.valueOf(n);
        sLen = s.length();

        int res = 0;
        // 当i小于sLen时，计算i位数的个数
        for (int i = 1; i < sLen; i++) {
            res += (int) Math.pow(dLen,i);
        }
        // 计算用digits组成sLen位且小于n的数的个数
        res += dfs(0);
        return res;
    }
    
    private int dfs(int p){
        if (p >= sLen){
            return 0;
        }
        int count = 0;
        int curLen = sLen - p;
        int first = s.charAt(p) - '0';
        // 只剩1位
        if (curLen == 1){
            for (int i = 0; i < dLen; i++) {
                if (d[i] <= first){ // 只剩1位的时候，如果小于等于first，需要计数+1
                    count++;
                }else{ // 剩下大的数不考虑
                    break;
                }
            }
        }else{
            for (int i = 0; i < dLen; i++) {
                if (d[i] < first){ // 如果d[i] < first，说明在i位填了d[i]后，后面的位都能填
                    count += (int) Math.pow(dLen, (curLen - 1));
                }else if (d[i] == first){ // 如果d[i] == first，说明在i位填了d[i]后，还要递归考虑后续的位能不能填
                    count += dfs(p+1);
                }else{ // 剩下大的数不考虑
                    break;
                }
            }
        }
        return count;
    }

}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] digits = {"1","2","3"};
        int n = 834;
        System.out.println(solution.atMostNGivenDigitSet(digits,n));
    }
}
