package lc.daily.date2210.Q769;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 *
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 *
 * 返回数组能分成的最多块数量。
 *
 *
 *
 * 示例 1:
 *
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 *
 *
 * 示例 2:
 *
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 *
 *
 * 提示:
 *
 * n == arr.length
 * 1 <= n <= 10
 * 0 <= arr[i] < n
 * arr中每个元素都 不同
 *
 * */
class Solution {
    // 贪心
    public int maxChunksToSorted(int[] arr) {
        int chunk = 0;
        int curMax = 0;
        for (int i = 0; i < arr.length; i++) {
            curMax = Math.max(curMax,arr[i]);
            if (curMax == i){
                chunk++;
            }
        }
        return chunk;
    }

    // 栈模拟
    public int maxChunksToSorted2(int[] arr) {
        Deque<int[]> deque = new ArrayDeque<>();
        for (int num : arr) {
            int max = num, min = num;
            // 比区间任何一个端点小就需要合并
            while (!deque.isEmpty() && (num < deque.getLast()[0] || num < deque.getLast()[1])){
                int[] interval = deque.removeLast();
                max = Math.max(max,interval[1]);
                min = Math.min(min,interval[0]);
            }
            deque.addLast(new int[]{min,max});
        }
        return deque.size();
    }
}


public class Main {
    public static void main(String[] args) {
        int[] arr = {1,0,2,3,4};
        Solution solution = new Solution();
        solution.maxChunksToSorted2(arr);
    }
}
