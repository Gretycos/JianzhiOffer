package com.daily.Q873;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 如果序列X_1, X_2, ..., X_n满足下列条件，就说它是斐波那契式的：
 *
 * n >= 3
 * 对于所有i + 2 <= n，都有X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr，找到 arr中最长的斐波那契式的子序列的长度。如果一个不存在，返回0 。
 *
 * （回想一下，子序列是从原序列 arr中派生出来的，它从 arr中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。
 * 例如，[3, 5, 8]是[3, 4, 5, 6, 7, 8]的一个子序列）
 *
 *
 *
 * 示例 1：
 *
 * 输入: arr = [1,2,3,4,5,6,7,8]
 * 输出: 5
 * 解释: 最长的斐波那契式子序列为 [1,2,3,5,8] 。
 * 示例2：
 *
 * 输入: arr = [1,3,7,11,12,14,18]
 * 输出: 3
 * 解释: 最长的斐波那契式子序列有 [1,11,12]、[3,11,14] 以及 [7,11,18] 。
 *
 *
 * 提示：
 *
 * 3 <= arr.length <= 1000
 * 1 <= arr[i] < arr[i + 1] <= 10^9
 *
 * */

class Solution {

    // time: O(n^2)
    // space: O(n^2)
    // dp定义：dp[i][j]：斐波那契数列的f[...A[j]A[i]]长度，i为最后一位的下标，j为倒数第二位的下标
    // 状态转移：dp[i][j] = dp[j][t] + 1, if (A[t]+A[j] == A[i])
    public int lenLongestFibSubseq2(int[] arr){
        int n = arr.length, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0 && arr[i] - arr[j] < arr[j]; j--) {
                // 查询有没有下一个数
                int t = map.getOrDefault(arr[i] - arr[j], -1);
                if (t >= 0) {
                    dp[i][j] = Math.max(3, dp[j][t] + 1);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    // time: O(n^2), worstTime: O(n^3)
    // space: O(n)
    public int lenLongestFibSubseq(int[] arr) {
        Set<Integer> set = new HashSet<>();
        int n = arr.length;
        for (int num : arr) {
            set.add(num);
        }
        int max = 0;
        for (int i = 0; i < n-2; i++){
            for (int j = i + 1; j < n-1; j++){
                int p1 = arr[i], p2 = arr[j];
                int len = 0;
                while(set.contains(p1+p2)){
                    int p3 = p1 + p2;
                    p1 = p2;
                    p2 = p3;
                    if (len == 0){
                        len += 3;
                    }else{
                        len ++;
                    }
                }
                max = Math.max(max,len);
            }
        }
        return max;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {2,4,7,8,9,10,14,15,18,23,32,50};
        System.out.println(solution.lenLongestFibSubseq2(arr));
    }
}
