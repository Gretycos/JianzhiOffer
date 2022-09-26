package lc.daily.date2209.Interview17Q19;


/**
 * 给定一个数组，包含从 1 到 N 所有的整数，但其中缺了两个数字。你能在 O(N) 时间内只用 O(1) 的空间找到它们吗？
 *
 * 以任意顺序返回这两个数字均可。
 *
 * 示例 1:
 *
 * 输入: [1]
 * 输出: [2,3]
 *
 *
 * 示例 2:
 *
 * 输入: [2,3]
 * 输出: [1,4]
 * 提示：
 *
 * nums.length <= 30000
 *
 * */


class Solution {
    public int[] missingTwo(int[] nums) {
        int n = nums.length + 2;
        int xor = 0; // 两个缺失的数异或的结果
        for (int num : nums) {
            xor ^= num;
        }
        for (int i = 1; i <= n; i++){
            xor ^= i;
        }
        int low_1 = xor & (-xor); // 找最后一个1的位置
        int[] res = new int[2];
        for (int num : nums) {
            if ((num & low_1) == 0){
                res[0] ^= num;
            }else{
                res[1] ^= num;
            }
        }
        for (int i = 1; i <= n; i++) {
            if ((i & low_1) == 0){
                res[0] ^= i;
            }else{
                res[1] ^= i;
            }
        }

        return res;
    }
}


public class Main {
}
