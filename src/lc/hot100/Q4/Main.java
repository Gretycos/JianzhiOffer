package lc.hot100.Q4;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 */


class Solution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 让nums1是较短的数组
        if (nums1.length > nums2.length) {
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }

        // 保证 m <= n
        int m = nums1.length, n = nums2.length;

        // m+n是偶数，leftSize = (m+n)/2 == (m+n+1)/2 (下整除)
        // m+n是奇数，leftSize = (m+n+1)/2
        int leftSize = (m + n + 1) / 2;

        // 在[0,m] 区间查找分割线，（左闭右闭）
        // 使得 nums1[mid1-1] <= nums2[mid2] && nums2[mid2-1] <= nums1[mid1]
        // 定义：mid1是第1个数组右边第1个元素的下标，mid1 = 第1个数组左边的元素个数
        //      mid2是第2个数组右边第1个元素的下标，mid2 = 第2个数组左边的元素个数
        // mid1 + mid2 == leftSize
        int left = 0, right = m;

        // 退出条件是left == right
        while (left < right) {
            int mid1 = left + (right - left) / 2; // 下取整 循环内部mid1取不到m
            int mid2 = leftSize - mid1;
            // nums1[mid1-1] <= nums2[mid2] && nums2[mid2-1] <= nums1[mid1] 取反后2选1
            // 如果选第1个条件取反，需要注意left = mid1导致死循环问题，需要让mid1上取整
            // 这里选择第2个条件取反，因为mid1默认下取整
            // 当进入只有2个元素的区间[left,right]，mid1 == left
            // 如果left = mid+1，则 left == right 退出循环
            // 如果rifht = mid1，则 left == right 退出循环
            if (nums1[mid1] < nums2[mid2 - 1]) {
                // 右边不满足条件，寻找右边区间[mid+1,right]
                left = mid1 + 1;
            } else {
                // 右边满足条件，寻找左边区间[left,mid1]
                right = mid1;
            }
        }
        // 退出循环
        int mid1 = left;
        int mid2 = leftSize - left;

        int nums1LeftMax = mid1 == 0 ? Integer.MIN_VALUE : nums1[mid1 - 1];
        int nums1RightMin = mid1 == m ? Integer.MAX_VALUE : nums1[mid1];
        int nums2LeftMax = mid2 == 0 ? Integer.MIN_VALUE : nums2[mid2 - 1];
        int nums2RightMin = mid2 == n ? Integer.MAX_VALUE : nums2[mid2];
        if ((m+n & 1) == 1){
            return Math.max(nums1LeftMax,nums2LeftMax);
        }
        return (Math.max(nums1LeftMax,nums2LeftMax) + Math.min(nums1RightMin,nums2RightMin)) / 2.0;
    }
}

public class Main {
    public static void main(String[] args) {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4,5,6,7,8};
        Solution solution = new Solution();
        System.out.println(solution.findMedianSortedArrays(nums1,nums2));
    }
}
