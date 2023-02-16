package lc.daily.date2302.Q2341;

import java.util.Arrays;

class Solution {
    public int[] numberOfPairs(int[] nums) {
        int[] map = new int[101];
        for (int num : nums) {
            map[num]++;
        }
        int[] res = new int[2];
        for (int count : map) {
            if (count != 0){
                res[0] += count / 2;
                if (count % 2 == 1){
                    res[1] ++;
                }
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {5,12,53,22,7,59,41,54,71,24,91,74,62,47,20,14,
                73,11,82,2,15,38,38,20,57,70,86,93,38,75,94,19,36,40,
                28,6,35,86,38,94,4,90,18,87,24,22};
        System.out.println(Arrays.toString(solution.numberOfPairs(nums)));
    }
}
