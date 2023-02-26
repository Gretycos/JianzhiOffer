package lc.labuladong.Others.BruteForce.Q51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<List<String>> res;
    private List<char[]> board;
    private int n;
    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        res = new LinkedList<>();
        board = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char[] initial = new char[n];
            Arrays.fill(initial, '.');
            board.add(initial);
        }
        backtrack(0);
        return res;
    }

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    private void backtrack(int row){
        // 结束条件：row 超过 board 的最后一行
        if (row == n){
            List<String> board = new LinkedList<>();
            for (char[] line : this.board) {
                board.add(new String(line));
            }
            res.add(board);
            return;
        }

        for (int col = 0; col < n; col++) {
            // 排除不合法选择
            if (!isValid(row, col)){
                continue;
            }
            // 做选择
            board.get(row)[col] = 'Q';
            // 进入下一行决策
            backtrack(row + 1);
            // 撤销选择
            board.get(row)[col] = '.';
        }
    }

    private boolean isValid(int row, int col){
        // 检查本列是否有皇后
        for (int i = 0; i <= row; i++){
            if (board.get(i)[col] == 'Q'){
                return false;
            }
        }
        // 检查右上是否有皇后
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board.get(i)[j] == 'Q'){
                return false;
            }
        }
        // 检查左上是否有皇后
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[j] == 'Q'){
                return false;
            }
        }
        return true;
    }
}

public class Main {
}
