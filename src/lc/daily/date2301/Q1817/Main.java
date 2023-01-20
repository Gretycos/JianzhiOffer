package lc.daily.date2301.Q1817;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            int id = log[0], time = log[1];
            map.putIfAbsent(id, new HashSet<>());
            map.get(id).add(time);
        }
        int[] res = new int[k];
        for (Set<Integer> minutes : map.values()) {
            int actVal = minutes.size();
            res[actVal - 1]++;
        }
        return res;
    }
}

public class Main {
}
