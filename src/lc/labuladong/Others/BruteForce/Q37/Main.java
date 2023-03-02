package lc.labuladong.Others.BruteForce.Q37;

class Solution {
    private char[][] board;
    private int m, n;
    public void solveSudoku(char[][] board) {
        this.board = board;
        m = board.length;
        n = board[0].length;
        backtrack(0,0);
    }

    private boolean backtrack(int i, int j){
        if (j == n){
            // 穷举到最后一列的话就换到下一行重新开始
            return backtrack(i+1, 0);
        }
        if (i == m){
            // 找到一个可行解，触发 base case
            return true;
        }
        if (board[i][j] != '.'){
            // 如果有预设数字，不用穷举
            return backtrack(i, j+1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(i, j, ch)){
                continue;
            }
            board[i][j] = ch;
            // 如果找到一个可行解，立即结束
            if (backtrack(i, j+1)){
                return true;
            }
            board[i][j] = '.';
        }
        // 穷举完 1~9，依然没有找到可行解，此路不通
        return false;
    }

    private boolean isValid(int r, int c, char ch){
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == ch) return false;
            // 判断列是否存在重复
            if (board[i][c] == ch) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r / 3) * 3 + i / 3][(c / 3) * 3 + i % 3] == ch) {
                return false;
            }
        }
        return true;
    }
}

public class Main {
}
