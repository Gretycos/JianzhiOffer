package lc.daily.date2208.Q658;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 排序好 的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 *
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = 3
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 *
 * 输入：arr = [1,2,3,4,5], k = 4, x = -1
 * 输出：[1,2,3,4]
 *
 *
 * 提示：
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length<= 10^4
 * arr按 升序 排列
 * -10^4<= arr[i], x <= 10^4
 *
 *
 * */

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        int l = 0, r = n - 1;
        int px = -1;
        // 二分查找x的位置
        while(l <= r){
            int m = l + (r - l) / 2;
            if (arr[m] == x){
                px = m;
                break;
            }
            if (arr[m] < x){
                l = m + 1;
            }else{
                r = m - 1;
            }
        }
        // 如果找到了
        if (px != -1){
            l = px; r = px+1;
        }else{ // 如果没找到，交换l和r的位置，因为循环退出的时候 l > r
            int t = l;
            l = r;
            r = t;
        }
        // 因为数组按升序排序，所以arr[l] <= x <= arr[r]，当l < r，一定有arr[l] <= arr[r]
        while(k > 0 && l >= 0 && r < n){
            // 满足条件
            if (x - arr[l] <= arr[r] - x){
                l--;
            }else{
                r++;
            }
            k--;
        }
        while(k > 0 && l >= 0){
            l--;
            k--;
        }
        while(k > 0 && r < n){
            r++;
            k--;
        }

        List<Integer> res = new ArrayList<>();
        // 把l和r之间的数加入结果集
        // 如果l在界外则i=0，如果r在界外则i < n
        for (int i = Math.max(l+1, 0); i < Math.min(r, n) ; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr = {1,2,5,5,7,9};
        int k = 4, x = -1;
        System.out.println(solution.findClosestElements(arr,k,x));
    }
}
