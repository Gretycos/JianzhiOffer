package lc.offer2.chapter6.jianzhi56_1;

import java.util.HashSet;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
 * 要求时间复杂度是O(n)，空间复杂度是O(1)。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * 示例 2：
 *
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 *
 *
 * 限制：
 *
 * 2 <= nums.length <= 10000
 *
 * */

class Solution {

    public int[] singleNumbers(int[] nums) {
        int[] res = new int[2];
        int t = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)){
                set.remove(num);
            }else{
                set.add(num);
            }
        }

        for (Integer integer : set) {
            res[t++] = integer;
        }

        return res;
    }

    public int[] singleNumbers2(int[] nums) {
        int[] res = new int[2];
        // 异或有交换律
        // digits的结果是两个只出现一次的数的异或结果
        // 因为出现两次的数异或都为0
        int digits = 0;
        for (int num : nums) {
            digits ^= num;
        }
        // 分割位，最后一个是1的位置
        int lastOne = (-digits) & digits;
        // 把所有数字分成2组，按照分割位是0还是1进行分割
        // 出现两次的会分在同一组
        // 两个只出现一次的数会分在不同的两组
        // 每组进行异或，最后剩下的就是出现1次的
        for (int num : nums) {
            // 该数字在分割位是0
            if ((num & lastOne) == 0){
                res[0] ^= num;
            } else {
                // 该数字在分割位是1
                res[1] ^= num;
            }
        }

        return res;
    }
}


public class Main {
}
