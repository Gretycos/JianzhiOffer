package lc.labuladong.Others.Classic.Q986;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res = new LinkedList<>();
        int i = 0, j = 0;
        while (i < firstList.length && j < secondList.length){
            int a1 = firstList[i][0], a2 = firstList[i][1];
            int b1 = secondList[j][0], b2 = secondList[j][1];
            // 两个区间存在交集
            if (b2 >= a1 && a2 >= b1){
                // 计算出交集，加入 res
                res.add(new int[]{Math.max(a1,b1), Math.min(a2,b2)});
            }
            // 指针前进
            if (b2 < a2){
                j++;
            }else {
                i++;
            }
        }
        return res.toArray(new int[0][]);
    }
}

public class Main {
}
