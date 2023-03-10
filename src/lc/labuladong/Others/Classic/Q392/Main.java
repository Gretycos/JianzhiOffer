package lc.labuladong.Others.Classic.Q392;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // 判断s是否为t的子串
    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        List<Integer>[] index = new List[26];
        // 先记下 t 中每个字符出现的位置
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (index[c - 'a'] == null) {
                index[c - 'a'] = new ArrayList<>();
            }
            index[c - 'a'].add(i);
        }

        // 串 t 上的指针
        int j = 0;
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            // 没有字符c
            if (index[c - 'a'] == null) return false;
            // 二分搜索区间中没有找到字符 c
            int pos = leftBound(index[c - 'a'], j);
            if (pos == -1) return false;
            // 向前移动指针 j
            j = index[c - 'a'].get(pos) + 1;
        }

        return true;
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
