package lc.daily.date2301.Q1807;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> map = new HashMap<>();
        for (List<String> entry : knowledge) {
            map.put(entry.get(0),entry.get(1));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c == '('){
                i++;
                int start = i;
                while (s.charAt(i) != ')'){
                    i++;
                }
                String key = s.substring(start, i);
                sb.append(map.getOrDefault(key,"?"));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "(a)(a)(a)aaa";
        List<List<String>> knowledge = new ArrayList<>();
        knowledge.add(List.of("a","yes"));
        System.out.println(solution.evaluate(s,knowledge));
    }
}
