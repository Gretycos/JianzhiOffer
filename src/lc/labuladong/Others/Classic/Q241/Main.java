package lc.labuladong.Others.Classic.Q241;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    private Map<String, List<Integer>> memo = new HashMap<>();
    public List<Integer> diffWaysToCompute(String expression) {
        if (memo.containsKey(expression)){
            return memo.get(expression);
        }

        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '-' || c == '*' || c == '+'){
                List<Integer> left = diffWaysToCompute(expression.substring(0,i));
                List<Integer> right = diffWaysToCompute(expression.substring(i+1));
                for (int a : left) {
                    for (int b : right) {
                        if (c == '+'){
                            res.add(a+b);
                        } else if (c == '-'){
                            res.add(a-b);
                        } else if (c == '*'){
                            res.add(a*b);
                        }
                    }
                }
            }
        }

        // base case
        if (res.isEmpty()){
            res.add(Integer.parseInt(expression));
        }
        return res;
    }
}

public class Main {
}
