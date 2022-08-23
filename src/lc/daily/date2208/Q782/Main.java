package lc.daily.date2208.Q782;

/**
 * 一个n x n的二维网络board仅由0和1组成。每次移动，你能任意交换两列或是两行的位置。
 *
 * 返回 将这个矩阵变为 “棋盘”所需的最小移动次数。如果不存在可行的变换，输出 -1。
 *
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 *
 *
 * 示例 1:
 *
 * [[0,1,1,0],
 *  [0,1,1,0],
 *  [1,0,0,1],
 *  [1,0,0,1]]
 *
 * 输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * 输出: 2
 * 解释:一种可行的变换方式如下，从左到右：
 * 第一次移动交换了第一列和第二列。
 * 第二次移动交换了第二行和第三行。
 *
 *
 * 示例 2:
 * [[0, 1],
 *  [1, 0]]
 *
 * 输入: board = [[0, 1], [1, 0]]
 * 输出: 0
 * 解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
 *
 *
 * 示例 3:
 * [[1, 0],
 *  [1, 0]]
 *
 * 输入: board = [[1, 0], [1, 0]]
 * 输出: -1
 * 解释: 任意的变换都不能使这个输入变为合法的棋盘。
 *
 *
 * 提示：
 *
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j]将只包含0或1
 *
 * */

class Solution {
    public int movesToChessboard(int[][] board) {
        if (!check(board)){
            return -1;
        }

        int[] col = new int[board.length];
        for(int i = 0; i < board.length; i++){
            col[i] = board[i][0];
        }
        // 行的交换和列的交换次数之和
        return calMinStep(board[0]) + calMinStep(col);
    }

    private boolean check(int[][] board){
        return checkFirst(board,true) && checkFirst(board,false)
                && checkRest(board,true) && checkRest(board,false);
    }

    private boolean checkFirst(int[][] board, boolean isRow){
        int count_0 = 0, count_1 = 0;
        for (int i = 0; i < board.length; i++){
            if ((isRow ? board[0][i] : board[i][0]) == 0){
                count_0 ++;
            }else{
                count_1 ++;
            }
        }
        return count_0 == count_1 || Math.abs(count_0 - count_1) == 1;
    }

    private boolean checkRest(int[][] board, boolean isRow){
        int same = 0, opposite = 0;
        int[] first; // 第一行/列
        if (isRow){
            first = board[0];
        }else{
            first = new int[board.length];
            for (int i = 0; i < board.length; i++) {
                first[i] = board[i][0];
            }
        }

        for (int i = 0; i < board.length; i++) {
            // 相同行/列，包含自身
            if ((isRow ? board[i][0] : board[0][i]) == first[0]){
                for (int j = 0; j < board.length; j++){
                    if ((isRow ? board[i][j] : board[j][i]) != first[j]){
                        return false;
                    }
                }
                same ++;
            }else{ // 相反
                for (int j = 0; j < board.length; j++) {
                    if ((isRow ? board[i][j] : board[j][i]) + first[j] != 1){
                        return false;
                    }
                }
                opposite ++;
            }
        }

        return same == opposite || Math.abs(same - opposite) == 1;
    }

    private int calMinStep(int[] first){
        // 假设 1010...开头
        int preNum = 1;
        int errors = 0; // 错位
        for (int num : first) {
            if (num != preNum){
                errors ++;
            }
            preNum = 1 - preNum; // 0 1 置换
        }
        int n = first.length;
        if ((n & 1) == 0){ // 长度为偶数
            // 类型可以是 101010 或 010101
            // 110010 -> 101010 errors == 2
            // 110010 -> 010101 errors == 4
            // or
            // 011001 -> 101010 errors == 4
            // 011001 -> 010101 errors == 2
            return Math.min(errors, n - errors) >> 1;
        }else{ // 长度为奇数
            // 类型取决于1多还是0多
            // 1多：10101 ，0多：01010
            // 11001 -> 10101 errors == 2
            // 11001 -> 01010 errors == 3
            if ((errors & 1) == 0){ // 错位数为偶数，意思是可以通过交换得到
                return errors >> 1;
            }else{ // 错位数为奇数，意思是这种类型无法通过交换得到，只能通过交换得到另一种1/0多的情况
                return (n - errors) >> 1;
            }
        }
    }


}


public class Main {
}
