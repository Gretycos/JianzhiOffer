package lc.daily.date2207.Q1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你个整数数组arr，其中每个元素都 不相同。
 *
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [4,2,1,3]
 * 输出：[[1,2],[2,3],[3,4]]
 * 示例 2：
 *
 * 输入：arr = [1,3,6,10,15]
 * 输出：[[1,3]]
 * 示例 3：
 *
 * 输入：arr = [3,8,-10,23,19,-4,-14,27]
 * 输出：[[-14,-10],[19,23],[23,27]]
 *
 *
 * 提示：
 *
 * 2 <= arr.length <= 10^5
 * -10^6 <= arr[i] <= 10^6
 *
 * */
class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        // Time: O(nlogn)
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length-1; i++){
            int gap = arr[i+1]-arr[i];
            if (gap <= min){
                if (gap < min){
                    res.clear();
                    min = gap;
                }
                List<Integer> temp = new ArrayList<>(Arrays.asList(arr[i], arr[i + 1]));
                res.add(temp);
            }
        }
        return res;
    }
}
public class Main {
}
