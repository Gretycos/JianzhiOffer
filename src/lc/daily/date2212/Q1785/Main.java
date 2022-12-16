package lc.daily.date2212.Q1785;

class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = Math.abs(sum - goal);
        // 如果能整除，返回diff / limit
        // 如果不能整除，说明还需要添加一个小于limit的数，返回diff / limit + 1
        return (int) ((diff + limit - 1) / limit);
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {-2,-2,-1,-1,-1,2,-1,0};
        int limit = 2;
        int goal = 778962318;
        System.out.println(solution.minElements(nums,limit,goal));
    }
}
