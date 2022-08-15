package lc.daily.date2207.Q593;

/**
 * 给定2D空间中四个点的坐标p1,p2,p3和p4，如果这四个点构成一个正方形，则返回 true 。
 *
 * 点的坐标pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 *
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 *
 *
 *
 * 示例 1:
 *
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 *
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 *
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *
 *
 * 提示:
 *
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4<= xi, yi<= 10^4
 *
 * */

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return f(p1, p2, p3, p4) || f(p1, p3, p2, p4) || f(p1, p4, p2, p3);
    }
    private boolean f(int[] p1, int[] p2, int[] p3, int[] p4){
        // 对角线是否相交
        if (!isCrossed(p1,p2,p3,p4)){
            return false;
        }
        // 对角线距离是否>0且是否相同
        if (!checkDistance(p1,p2,p3,p4)){
            return false;
        }

//        // 出现平行x轴的对角线
//        if (isParallelAxis(p1,p2) == 0){
//            return isParallelAxis(p3, p4) == 1;
//        }
//        if (isParallelAxis(p3,p4) == 0){
//            return isParallelAxis(p1, p2) == 1;
//        }
//
//        // 出现平行y轴的对角线
//        if (isParallelAxis(p1,p2) == 1){
//            return isParallelAxis(p3, p4) == 0;
//        }
//        if (isParallelAxis(p3,p4) == 1){
//            return isParallelAxis(p1, p2) == 0;
//        }


        // 对角线是否垂直
        return isPerpendicular(p1,p2,p3,p4);
    }

    private int isParallelAxis(int[] start, int[] end){
        // 平行x轴
        if (start[1] == end[1]){
            return 0;
        }
        // 平行y轴
        if (start[0] == end[0]){
            return 1;
        }
        return -1;
    }

    private boolean isCrossed(int[] p1, int[] p2, int[] p3, int[] p4){
        return (p1[0] + p2[0]) == (p3[0] + p4[0]) && (p1[1] + p2[1]) == (p3[1] + p4[1]);
    }

    private boolean checkDistance(int[] p1, int[] p2, int[] p3, int[] p4){
        int l1 = ((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
        int l2 = ((p3[0] - p4[0]) * (p3[0] - p4[0]) + (p3[1] - p4[1]) * (p3[1] - p4[1]));
        return l1 > 0 && l1 == l2;
    }

    private boolean isPerpendicular(int[] p1, int[] p2, int[] p3, int[] p4){
        // 向量数量积为0
        return (p1[0] - p2[0]) * (p3[0] - p4[0]) + (p1[1] - p2[1]) * (p3[1] - p4[1]) == 0;
    }

}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] p1 = {0,0},
              p2 = {0,0},
              p3 = {0,0},
              p4 = {0,0};
        System.out.println(solution.validSquare(p1,p2,p3,p4));
    }
}
