package lc.daily.date2210.Q870;

import java.util.Arrays;

/**
 * 给定两个大小相等的数组nums1和nums2，nums1相对于 nums2 的优势可以用满足nums1[i] > nums2[i]的索引 i的数目来描述。
 *
 * 返回 nums1的任意排列，使其相对于 nums2的优势最大化。
 *
 *
 *
 * 示例 1：
 * 输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
 * 输出：[2,11,7,15]
 *
 *
 * 示例 2：
 * 输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
 * 输出：[24,32,8,12]
 *
 *
 * 提示：
 * 1 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 10^9
 *
 * */

class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        // nums2的下标，因为数组元素不可变，因此可以让下标排序
        Integer[] idx2 = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx2[i] = i;
        }
        Arrays.sort(nums1);
        Arrays.sort(idx2,(i,j) -> nums2[i]-nums2[j]);

        int[] res = new int[n];
        int l = 0, r = n-1;
        for (int num : nums1) {
            if (num > nums2[idx2[l]]) { // 打得过，直接counter
                res[idx2[l++]] = num;
            } else { // 打不过，用当前数字counter nums2中当前最大的数字
                res[idx2[r--]] = num;
            }
        }
        return res;
    }
}


public class Main {
}
