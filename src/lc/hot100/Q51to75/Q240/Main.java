package lc.hot100.Q51to75.Q240;


class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n-1;
        while(i < m && j >= 0){
            int num = matrix[i][j];
            if (target == num){
                return true;
            }
            if (target > num){
                i++;
            }else{
                j--;
            }
        }
        return false;
    }
}


public class Main {
}
