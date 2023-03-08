package lc.labuladong.Others.Classic.Q969;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<Integer> res; // 记录反转操作序列

    public List<Integer> pancakeSort(int[] arr) {
        res = new LinkedList<>();
        sort(arr, arr.length);
        return res;
    }

    private void sort(int[] cakes, int n) {
        // base case
        if (n == 1) return;

        // 寻找最大饼的索引
        int maxCake = 0, maxCakeIdx = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCake = cakes[i];
                maxCakeIdx = i;
            }
        }

        // 第一次翻转，将最大饼翻到最上面
        reverse(cakes, 0, maxCakeIdx);
        res.add(maxCakeIdx + 1);
        // 第二次翻转，将最大饼翻到最下面
        reverse(cakes, 0, n - 1);
        res.add(n);
        // 递归调用
        sort(cakes, n - 1);
    }

    private void reverse(int[] arr, int i, int j) {
        for (; i < j; i++, j--) {
            int t = arr[i];
            arr[i] = arr[j];
            arr[j] = t;
        }
    }
}

public class Main {
}
