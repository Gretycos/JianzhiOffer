package lc.daily.date2301.Q1664;

class Solution {
    public int waysToMakeFair(int[] nums) {
        int sOdd = 0,sEven = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0){
                sEven += nums[i];
            }else{
                sOdd += nums[i];
            }
        }
        int preOdd = 0, preEven = 0;
        int res = 0;
        // 删除一个数nums[i]后，i后面的奇偶下标会互换，i前面的不会变化
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0){
                // curOddSum = preOdd + sufEven
                //           = preOdd + (sEven - preEven) - nums[i]
                // curEvenSum = preEven + sufOdd
                //            = preEven + (sOdd - preOdd)
                if (preOdd + sEven - preEven - nums[i] == preEven + sOdd - preOdd){
                    res++;
                }
                preEven += nums[i];
            } else {
                // curOddSum = preOdd + sufEven
                //           = preOdd + (sEven - preEven)
                // curEvenSum = preEven + sufOdd
                //            = preEven + (sOdd - preOdd) - nums[i]
                if (preOdd + sEven - preEven == preEven + sOdd - preOdd - nums[i]){
                    res++;
                }
                preOdd += nums[i];
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,1,1};
        System.out.println(solution.waysToMakeFair(nums));
    }
}
