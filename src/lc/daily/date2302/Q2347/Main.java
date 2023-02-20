package lc.daily.date2302.Q2347;

import java.util.List;

class Solution {
    public String bestHand(int[] ranks, char[] suits) {
        int[] nums = new int[13];
        int[] patterns = new int[4];
        for (int i = 0; i < 5; i++) {
            int rank = ranks[i] - 1;
            int pattern = suits[i] - 'a';
            nums[rank]++;
            if (++patterns[pattern] == 5) return "Flush";
        }
        boolean pair = false;
        for (int num : nums) {
            if (num >= 3) return "Three of a Kind";
            if (num == 2) pair = true;
        }
        return pair ? "Pair" : "High Card";
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ranks = {12,12,12,9,9};
        char[] suits = {'b','d','a','c','b'};
        System.out.println(solution.bestHand(ranks,suits));
    }
}
