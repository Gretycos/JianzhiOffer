package lc.offer2.chapter2.jianzhi12;

/**
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 *
 * (A) (B) (C) E
 *  S   F  (C) S
 *  A  (D) (E) E
 *
 * 示例 1：
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 *
 *
 * */

class Solution {
    // 方向矩阵
    private final int[][] d = {
            {-1,0},
            {0,1},
            {1,0},
            {0,-1}
    };

    // 访问矩阵
    private boolean[][] isVisited;

    private boolean hasPath(char[][] board, String word, int i, int j, int p){
        // 当前位置的字符与p指向的字符不同
        if (board[i][j] != word.charAt(p)){
            return false;
        }

        // 找到字符串
        if (p == word.length() -1){
            return true;
        }

        isVisited[i][j] = true; // 标记访问

        // 依次对邻居进行深度优先遍历
        for (int k = 0; k < 4; k++) {
            // 邻居的坐标
            int x = i + d[k][0],
                y = j + d[k][1];

            // 在矩阵的界内 而且 未访问过
            if (x >= 0 && x < board.length && y >=0 && y < board[0].length && !isVisited[x][y]){
                if (hasPath(board,word,x,y,p+1)){
                    return true;
                }
            }
        }

        // 如果访问了4个邻居都没有找到，则回溯
        isVisited[i][j] = false; // 当前点标记为未访问
        return false;
    }

    // 时间O(MN*3^l) l是单词长，有4个方向，但不能回头，所以是3^l
    // 空间O(MN)
    public boolean exist(char[][] board, String word) {
        if (board.length * board[0].length < word.length()){
            return false;
        }

        isVisited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 指针p指向字符串开始的位置
                if(hasPath(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','E','S'},
                {'A','D','E','E'}
        };
        String word = "ABCESEEEF";
        System.out.println(solution.exist(board,word));
    }
}
