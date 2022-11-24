package lc.daily.date2211.Q795;

class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0, lastEq = -1, lastGt = -1;
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (cur >= left && cur <= right){
                lastEq = i;
            }else if (cur > right){
                lastGt = i;
                lastEq = -1;
            }
            if (lastEq != -1){
                res += lastEq - lastGt;
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {876,880,482,260,132,421,732,703,795,420,871,445,400,291,358,589,617,202,755,810,227,813,549,791,418,528,835,401,526,584,873,662,13,314,988,101,299,816,833,224,160,852,179,769,646,558,661,808,651,982,878,918,406,551,467,87,139,387,16,531,307,389,939,551,613,36,528,460,404,314,66,111,458,531,944,461,951,419,82,896,467,353,704,905,705,760,61,422,395,298,127,516,153,299,801,341,668,598,98,241};
        int left = 658, right = 719;
        System.out.println(solution.numSubarrayBoundedMax(nums,left,right));
    }
}
