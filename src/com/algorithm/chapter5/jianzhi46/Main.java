package com.algorithm.chapter5.jianzhi46;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *  
 *
 * 提示：
 *
 * 0 <= num < 231
 *
 * */

class Solution {


    // 青蛙跳台阶变种
    // 动态规划
    public int translateNum2(int num) {
        String s = String.valueOf(num);
        int pre = 1, cur = 1;
        for (int i = 2; i < s.length() + 1; i++) {
            int digits = Integer.parseInt(s.substring(i-2,i));
            if (digits >= 10 && digits <= 25){
                int t = cur;
                cur = pre + cur;
                pre = t;
            }else{
                pre = cur;
            }

        }
        return cur;
    }

    private int count;
    private String s;
    // 递归回溯
    private void translateNumCore(int p){
        if (p == s.length()){
            count++;
            return;
        }
        translateNumCore(p+1);
        if (p < s.length() - 1){ // 考虑2位
            int digits = Integer.parseInt(s.substring(p, p + 2));
            if (digits >= 10 && digits <= 25){
                translateNumCore(p+2);
            }
        }
    }
    public int translateNum(int num) {
        count = 0;
        s = String.valueOf(num);
        translateNumCore(0);
        return count;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.translateNum(12258));
    }
}
