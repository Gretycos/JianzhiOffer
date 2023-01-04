package lc.daily.date2301.Q1802;

class Solution {
    public int maxValue(int n, int index, int maxSum) {
        // 二分搜索idx对应的最大值[left,right]
        int left = 1, right = maxSum;
        // 搜索右边界
        while (left <= right){
            int max = left + (right - left) / 2;
            long sum = getSum(max - 1, index) + getSum(max, n - index);
            if (sum > maxSum){
                right = max - 1;
            }else if (sum < maxSum){
                left = max + 1;
            }else if (sum == maxSum){
                left = max + 1;
            }
        }
        return right;
    }

    // 计算最大值为x, 元素个数为n的数组的和
    // 和可能超过INT_MAX
    private long getSum(long x, int n){
        return n >= x ? (x + 1) * x / 2 + n - x : (x + x - n + 1) * n / 2;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3, index = 0, maxSum = 10;
        System.out.println(solution.maxValue(n,index,maxSum));
    }
}
