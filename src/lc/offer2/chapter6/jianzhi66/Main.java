package lc.offer2.chapter6.jianzhi66;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。
 * 不能使用除法。
 *
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 *
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * */

class Solution {
    public int[] constructArr(int[] a) {
        if (a.length == 0) return new int[0];
        int[] res = new int[a.length];
        // 先用res[i]记录i左边的元素累积
        res[0] = 1; // 第一个元素的左边没有元素，所以累积是1
        for (int i = 1; i < a.length; i++) {
            res[i] = res[i-1] * a[i-1];
        }

        // R 记录i右边的累积
        int R = 1; // 最后一个的元素右边没有元素，所以累积是1
        for (int i = a.length-1; i >= 0; i--) {
            // res[i]原本是是i左边的元素的累积
            // 再乘以i右边的元素的累积R，得到最终结果
            res[i] *= R;
            // R往前移动并累积
            R *= a[i];
        }

        return res;
    }
}
public class Main {
}
