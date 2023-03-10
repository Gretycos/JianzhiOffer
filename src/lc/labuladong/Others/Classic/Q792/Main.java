package lc.labuladong.Others.Classic.Q792;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int numMatchingSubseq(String s, String[] words) {
        List<Integer>[] index = new List[26];
        for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
            if (index[c-'a'] == null){
                index[c-'a'] = new ArrayList<>();
            }
            index[c-'a'].add(i);
        }

        int res = 0;
        for (String word : words) {
            // 字符串 word 上的指针
            int i = 0;
            // 串 s 上的指针
            int j = 0;
            for (; i < word.length(); i++) {
                char c = word.charAt(i);
                // s中没有字符c
                if (index[c-'a'] == null){
                    break;
                }
                int pos = leftBound(index[c-'a'], j);
                // 搜索区间没找到c
                if (pos == -1) {
                    break;
                }
                j = index[c-'a'].get(pos) + 1;
            }
            // 如果 word 完成匹配，则是子序列
            if (i == word.length()){
                res++;
            }
        }
        return res;
    }

    // 查找左侧边界的二分查找
    private int leftBound(List<Integer> arr, int target) {
        int left = 0, right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > arr.get(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (left == arr.size()) {
            return -1;
        }
        return left;
    }
}

public class Main {
}
