package lc.daily.date2302.Q2363;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        int[] valueToWeight = new int[1001];
        for (int[] item : items1) {
            valueToWeight[item[0]] += item[1];
        }
        for (int[] item : items2) {
            valueToWeight[item[0]] += item[1];
        }
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < valueToWeight.length; i++) {
            if (valueToWeight[i] != 0){
                res.add(List.of(i, valueToWeight[i]));
            }
        }
        return res;
    }
}

public class Main {
}
