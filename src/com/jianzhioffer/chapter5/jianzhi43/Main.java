package com.jianzhioffer.chapter5.jianzhi43;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 *
 *
 * 示例 1：
 *
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 *
 * 输入：n = 13
 * 输出：6
 *
 *
 * 限制：
 *
 * 1 <= n < 2^31
 *
 *
 * */

class Solution {
    private int countDigitOneCore(String  s, int p){
        int first = s.charAt(p) - '0';
        int length = s.length() - p;

        // 保证了length >= 1
        if (length == 1){
            if (first == 0){
                return 0;
            }
            return 1;
        }

        // 21345
        // 21345:[1~1345] [1346~21345]

        // [1346~21345]
        // 1在最高位出现的次数
        int firstDigitCount = 0;
        if (first > 1){ // 首位不是1
            // 10000~19999中1在最高位出现的次数=10^(5-1)
            firstDigitCount = (int) Math.pow(10, length - 1);
        } else if (first == 1){ // 首位本身就是1
            // 1345
            // 1000~1345中1在最高位出现的次数=345+1
            firstDigitCount = Integer.parseInt(s.substring(p + 1)) + 1;
        }

        // 1出现在其他位上，选择其中一位是1，剩下的的位从0~9中选择
        // 首位从0~first-1中选一个，然后从剩下的length-1个位置中选一个放置1，再在每个剩下的空位上从0~9选数字填充
        // [1346~21345]:[1346~11345] [11346~21345]两个长度为10^(5-1)的区间
        // 因为出现了进位，所以低位能在0~9中自由选择
        // 因此可以转换为[0 0000~0 9999] [1 0000~ 1 9999] 两个区间
        // first为0或1：2种，剩下的4位数选择一个位置放1：C(length-1,1)种，剩下3个位置0~9中任意选择：10*10*10=10^(length-2)
        int otherDigitsCount = first * (length - 1) * (int) Math.pow(10, Math.max(length - 2, 0));

        // [1~1345]
        int recursiveCount = countDigitOneCore(s,p + 1);

        return firstDigitCount + otherDigitsCount + recursiveCount;
    }

    // 进制修改可以换base
    private int PowerBase10(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }


    public int countDigitOne(int n) {
        String str = Integer.toString(n);
        return countDigitOneCore(str,0);
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.countDigitOne(12345));
    }
}
