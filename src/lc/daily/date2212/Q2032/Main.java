package lc.daily.date2212.Q2032;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] map = new int[101];
        for (int num : nums1) {
            map[num] = 1;
        }
        for (int num : nums2) {
            map[num] = switch (map[num]){
                case 1,5 -> 5;
                default -> 2;
            };
        }
        for (int num : nums3) {
            map[num] = switch (map[num]){
                case 1,2,5 -> 5;
                default -> 3;
            };
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < map.length; i++) {
            if (map[i] == 5){
                res.add(i);
            }
        }
        return res;
    }
}
public class Main {
    public static void main(String[] args) {
        int[] nums1 = {9,11,15,5};
        int[] nums2 = {1,5,5,12,4,8,3,4,5,10};
        int[] nums3 = {8};
        Solution solution = new Solution();
        solution.twoOutOfThree(nums1,nums2,nums3);
    }
}
