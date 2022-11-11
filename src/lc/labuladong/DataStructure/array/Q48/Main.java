package lc.labuladong.DataStructure.array.Q48;

class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 沿主对角线镜像
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        }

        for (int[] row : matrix) {
            reverse(row);
        }
    }

    private void reverse(int[] arr){
        int l = 0, r = arr.length - 1;
        while (l < r){
            int t = arr[l];
            arr[l++] = arr[r];
            arr[r--] = t;
        }
    }
}
public class Main {
}
