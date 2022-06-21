package com.jianzhioffer.chapter3.jianzhi17;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 *
 * */

class Solution {
    public int[] printNumbers2(int n) {
        int len = (int) Math.pow(10,n) - 1;
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = i+1;
        }
        return res;
    }

    private char[] num;
    private List<String> res;

    private void dfs(int n, int p){
        if (p == n){
            // 去前导0
            int i = 0;
            while(i < n && num[i] == '0'){
                i++;
            }
            // 0 不输出
            if (i==n)
                return;
            res.add(String.valueOf(num,i,n-i));
            return;
        }

        for (int i = 0; i < 10; i++) {
            num[p] = (char) (i + '0');
            dfs(n,p+1);
        }
    }


    // 大数问题
    // 时间O(10^n)
    // 空间(10^n)
    public List<String> printNumbers(int n) {
        try{
            if(n > 7){
                throw new Exception("Large Number");
            }else{
                num = new char[n];
                res = new ArrayList<>();
                dfs(n,0);
                return res;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<String> res = solution.printNumbers(7);
        if(res != null){
            System.out.println(res.get(res.size()-1));
        }
    }
}
