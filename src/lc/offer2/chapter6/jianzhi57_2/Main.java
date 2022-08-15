package lc.offer2.chapter6.jianzhi57_2;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 * */


class Solution {

    // 时间O(target) 空间O(1)
    public int[][] findContinuousSequence(int target) {
        ArrayList<int[]> res = new ArrayList<>();
        int sum;
        int[] temp;

        int l = 1, r = 2;

        while(l < (target + 1) / 2 ){
            // 等差数列求和
            sum = (l + r) * (r - l + 1) / 2;
            // 和相等
            if (sum == target){
                // 把双指针区间的数字集加入结果
                temp = new int[r - l + 1];
                for (int i = 0; i < temp.length; i++) {
                    temp[i] = l + i;
                }
                res.add(temp);
                l ++;
            }
            else if (sum < target) {
                // 和不够大
                r++;
            } else {
                // 和太大了
                l++;
            }
        }

        return res.toArray(new int[0][0]);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.deepToString(solution.findContinuousSequence(15)));
    }
}
