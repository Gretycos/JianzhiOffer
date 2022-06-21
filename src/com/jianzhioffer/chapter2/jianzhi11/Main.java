package com.jianzhioffer.chapter2.jianzhi11;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 *
 * 给你一个可能存在重复元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
 * 请返回旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为 1。
 *
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numbers = [3,4,5,1,2]
 * 输出：1
 *
 * 示例 2：
 *
 * 输入：numbers = [2,2,2,0,1]
 * 输出：0
 *
 *
 * 提示：
 *
 * n == numbers.length
 * 1 <= n <= 5000
 * -5000 <= numbers[i] <= 5000
 * numbers 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * */

class Solution {

    private int findMin(int[] numbers, int m, int n){
        int result = numbers[m];
        for (int i = m+1; i <= n; i++) {
            result = Math.min(numbers[i],result);
        }
        return result;
    }

    public int minArray2(int[] numbers) {
        int m = 0;
        int n = numbers.length-1;
        int p = 0;


        if (numbers[m] < numbers[n]){ // 有序
            return numbers[m];
        }

        while (numbers[m] >= numbers[n]){ // 开头比末尾大
            if (n - m == 1){
                return numbers[n];
            }

            p = (m + n) / 2;

            if (numbers[m] == numbers[n] && numbers[n] == numbers[p]){
                return findMin(numbers, m, n);
            }

            if (numbers[p] >= numbers[m]){ // 中点比开头大，说明前半部分递增或相同，移动开头至中点
                m = p;
            } else if (numbers[p] <= numbers [n]){ // 中点比末尾小，说明后半部分递增或相同，移动末尾至中点
                n = p;
            }
        }

        return numbers[p];
    }

    public int minArray(int[] numbers) {
        int l = 0, r = numbers.length - 1;
        while(l <= r){
            int m  = l + (r - l) / 2;
            if (numbers[m] > numbers[r]){
                l = m + 1;
            } else if (numbers[m] < numbers[r]){
                r = m;
            } else {
                r --;
            }
        }
        return numbers[l];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] numbers = {10,1,10,10,10};
        System.out.println(solution.minArray(numbers));
    }
}
