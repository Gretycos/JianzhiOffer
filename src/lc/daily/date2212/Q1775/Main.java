package lc.daily.date2212.Q1775;

class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        if (6 * nums1.length < nums2.length || 6 * nums2.length < nums1.length) {
            return -1;
        }
        int d = 0; // sum2 - sum1
        for (int num : nums1) {
            d -= num;
        }
        for (int num : nums2) {
            d += num;
        }
        // 让nums1指向和更小的数组
        if (d < 0){
            d = -d;
            int[] t = nums1;
            nums1 = nums2;
            nums2 = t;
        }

        // 两个数组的数字变化量
        int[] changes = new int[6];
        // nums1的数要增大
        for (int num : nums1) {
            changes[6 - num] ++;
        }
        // nums2的数要减小
        for (int num : nums2) {
            changes[num - 1]++;
        }
        int res = 0;
        for (int i = 5; i >= 0; i--) {
            if (d > i * changes[i]){
                res += changes[i];
                d -= i * changes[i];
            }else{
                res += (d + (i - 1)) / i; // 上取整(d/i)
                break;
            }
        }
        return res;

    }
}
public class Main {
}
