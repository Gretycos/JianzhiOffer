package lc.labuladong.Others.BruteForce.Q773;

import java.util.*;

class Solution {
    private int m, n;
    public int slidingPuzzle(int[][] board) {
        m = board.length;
        n = board[0].length;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        String target = "123450";

        // 记录一维字符串的相邻索引
        List<Integer>[] neighbor = getNeighbor();

        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        q.add(start);

        int step = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.remove();
                // 判断是否达到目标局面
                if (cur.equals(target)){
                    return step;
                }
                visited.add(cur);
                // 找到数字 0 的索引
                int idx = cur.indexOf('0');
                for (int adj : neighbor[idx]) {
                    String newBoard = swap(cur, idx, adj);
                    // 防止走回头路
                    if (!visited.contains(newBoard)){
                        q.add(newBoard);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(String cur, int i, int j){
        char[] chs = cur.toCharArray();
        char t = chs[i];
        chs[i] = chs[j];
        chs[j] = t;
        return new String(chs);
    }

    private List<Integer>[] getNeighbor(){
        List<Integer>[] res = new List[m * n];
        for (int i = 0; i < res.length; i++) {
            res[i] = new ArrayList<>(4);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int cur = i * n + j;
                // 右
                if (cur+1 < i * n + n){
                    res[cur].add(cur+1);
                }
                // 左
                if (cur-1 >= i * n){
                    res[cur].add(cur-1);
                }
                // 上
                if (cur-n >= 0){
                    res[cur].add(cur-n);
                }
                // 下
                if (cur+n < m * n){
                    res[cur].add(cur+n);
                }
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = {
                {1,2,3},
                {4,0,5}
        };
        System.out.println(solution.slidingPuzzle(board));
    }
}
