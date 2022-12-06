package lc.daily.date2212.Q1805;

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>(); // 去重
        for (int i = 0; i < word.length();) {
            char c = word.charAt(i);
            if (c >= '0' && c <= '9'){
                boolean isBeginning = true; // 寻找开头
                // 双指针
                int start = i, end = i;
                while (i < word.length() && word.charAt(i) >= '0' && word.charAt(i) <= '9'){
                    char num = word.charAt(i);
                    if (isBeginning){
                        if (num != '0'){ // 发现第一个不是0的元素
                            isBeginning = false;
                            start = i;
                            end = i;
                        }
                    }else{
                        end = i;
                    }
                    i++;
                }
                set.add(word.substring(start,end + 1));
            }else{
                i++;
            }
        }
        return set.size();
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numDifferentIntegers("035985750011523523129774573439111590559325a1554234973"));
    }
}
