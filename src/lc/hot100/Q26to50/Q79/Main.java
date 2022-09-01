package lc.hot100.Q26to50.Q79;

/**
 * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
 *
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 * 示例 3：
 *
 *
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *
 *
 * 提示：
 *
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 *
 * */

class Solution {
    private char[][] board;
    private int m,n;
    private String word;

    public boolean exist(char[][] board, String word) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        this.word = word;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(dfs(i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(int i, int j, int p){
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] == '#'
                || p >= word.length() || board[i][j] != word.charAt(p)){
            return false;
        }
        if (p == word.length()-1){
            return true;
        }
        board[i][j] = '#';
        boolean isExisted = dfs(i-1,j,p+1)
                || dfs(i,j+1,p+1)
                || dfs(i+1,j,p+1)
                || dfs(i,j-1,p+1);
        board[i][j] = word.charAt(p);
        return isExisted;
    }

}


public class Main {
}
