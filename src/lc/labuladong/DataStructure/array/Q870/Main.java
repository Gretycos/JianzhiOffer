package lc.labuladong.DataStructure.array.Q870;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        PriorityQueue<int[]> maxQ = new PriorityQueue<>((a,b) -> b[1] - a[1]);
        for (int i = 0; i < n; i++) {
            maxQ.add(new int[]{i, nums2[i]});
        }
        Arrays.sort(nums1);

        int left = 0, right = n - 1;
        int[] res = new int[n];
        while (!maxQ.isEmpty()){
            int[] pair = maxQ.remove();
            int i = pair[0], val = pair[1];
            if (nums1[right] > val){ // 打得过
                res[i] = nums1[right--];
            }else{ // 打不过
                res[i] = nums1[left++];
            }
        }
        return res;
    }
}
public class Main {
}
