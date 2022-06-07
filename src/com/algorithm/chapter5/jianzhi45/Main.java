package com.algorithm.chapter5.jianzhi45;


import java.util.ArrayList;
import java.util.List;


/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 *
 *
 * 示例 1:
 *
 * 输入: [10,2]
 * 输出: "102"
 * 示例2:
 *
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 *
 * 提示:
 *
 * 0 < nums.length <= 100
 * 说明:
 *
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 *
 * */


class Solution {
    public String minNumber(int[] nums) {
        List<String> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int num: nums){
            list.add(String.valueOf(num));
        }
        // 如果ab < ba 则 a < b
        list.sort((str1,str2) -> (str1+str2).compareTo(str2+str1));
        for (String s : list) {
            stringBuilder.append(s);
        }

        return stringBuilder.toString();
    }
}


public class Main {
    public static void main(String[] args) {

    }
}
