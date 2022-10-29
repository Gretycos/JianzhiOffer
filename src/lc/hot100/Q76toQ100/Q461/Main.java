package lc.hot100.Q76toQ100.Q461;

/**
 * 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 *
 *
 *
 * 示例 1：
 *
 * 输入：x = 1, y = 4
 * 输出：2
 * 解释：
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 *
 * 示例 2：
 *
 * 输入：x = 3, y = 1
 * 输出：1
 *
 *
 * 提示：
 *
 * 0 <= x, y <= 2^31 - 1
 *
 * */

class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y; // 求异或
        int dist = 0;
        // 数异或后1的个数
        for(int i = 0, p = 1; i < 32; i++, p <<= 1){
            if((xor & p) != 0) dist++;
        }
        return dist;
    }
}
public class Main {
}
