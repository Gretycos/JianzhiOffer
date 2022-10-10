package lc.daily.date2210.Q801;

/**
 * 我们有两个长度相等且不为空的整型数组nums1和nums2。在一次操作中，我们可以交换nums1[i]和nums2[i]的元素。
 *
 * 例如，如果 nums1 = [1,2,3,8] ， nums2 =[5,6,7,4] ，你可以交换 i = 3 处的元素，得到 nums1 =[1,2,3,4] 和 nums2 =[5,6,7,8] 。
 * 返回 使 nums1 和 nums2 严格递增所需操作的最小次数 。
 *
 * 数组arr严格递增 且arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1]。
 *
 * 注意：
 *
 * 用例保证可以实现操作。
 *
 *
 * 示例 1:
 *
 * 输入: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
 * 输出: 1
 * 解释:
 * 交换 A[3] 和 B[3] 后，两个数组如下:
 * A = [1, 3, 5, 7] ， B = [1, 2, 3, 4]
 * 两个数组均为严格递增的。
 *
 *
 * 示例 2:
 *
 * 输入: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
 * 输出: 1
 *
 *
 * 提示:
 *
 * 2 <= nums1.length <= 10^5
 * nums2.length == nums1.length
 * 0 <= nums1[i], nums2[i] <= 2 * 10^5
 *
 * */

class Solution {
    // 状态机dp
    public int minSwap(int[] nums1, int[] nums2) {
        // 定义：changed[i]：前i个元素严格递增所需的最小操作次数，本轮交换
        //      unchanged[i]：前i个元素严格递增所需的最小操作次数，本轮不交换
        // 初始化：第一个元素变化的操作次数为1，不变化为0
        int changed = 1, unchanged = 0;
        for (int i = 1; i < nums1.length; i++) {
            int tChanged = changed, tUnchanged = unchanged;
            if (nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]
                    && nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]){ // 平行、交叉满足要求
                unchanged = Math.min(tUnchanged,tChanged);
                changed = Math.min(tChanged+1, tUnchanged+1);
            }else if (nums1[i] > nums1[i-1] && nums2[i] > nums2[i-1]){ // 平行满足要求
                unchanged = tUnchanged;
                changed = tChanged + 1;
            }else if (nums1[i] > nums2[i-1] && nums2[i] > nums1[i-1]){ // 交叉满足要求
                unchanged = tChanged;
                changed = tUnchanged + 1;
            }
        }
        return Math.min(changed,unchanged);
    }
}


public class Main {
}
