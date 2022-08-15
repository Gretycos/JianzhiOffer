package lc.offer2.chapter6.jianzhi56_2;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * 示例 2：
 *
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 *
 * 限制：
 *
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * */


class Solution {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.get(num) == null){
                map.put(num,1);
            }else{
                int count = map.get(num);
                if (count + 1 == 3){
                    map.remove(num);
                }else{
                    map.put(num,count+1);
                }

            }
        }
        int res = -1;
        for (Integer value : map.keySet()) {
            res =  value;
        }

        return res;
    }

    // 位运算
    public int singleNumber2(int[] nums) {
        int [] k = new int[32];
        for (int num : nums) {
            for (int bit = 0; bit < 32; bit++) {
                // 最后一位是1，则数组对应位加1
                k[bit] += (num >> bit & 1) == 1 ? 1 : 0;
            }
        }
        int res = 0;
        for(int i = 31; i >= 0 ;i--){
            // 左移一位，填入0
            res <<= 1;
            // 如果该位只和不能被3整除，说明遇到了只出现一次的数字的那一位
            if(k[i] % 3 == 1){
                // 该位的最后一位填上1
                res |= 1;
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {9,3,7,9,7,9,7};
        System.out.println(solution.singleNumber(nums));
    }
}
