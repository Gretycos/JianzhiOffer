package lc.daily.date2211.Q808;

class Solution {
    private double[][] cache;
    public double soupServings(int n) {
        if (n > 4450) return 1.0;
        n = (n + 24) / 25; // 上取整
        cache = new double[n+1][n+1];
        return dfs(n, n);
    }

    private double dfs(int a, int b){
        if (a <= 0 && b <= 0){
            return 0.5;
        }
        if (a <= 0){
            return 1;
        }
        if (b <= 0){
            return 0;
        }
        if (cache[a][b] == 0){
            cache[a][b] = 0.25 * (dfs(a - 4, b)
                    + dfs(a - 3, b - 1)
                    + dfs(a - 2, b - 2)
                    + dfs(a - 1, b - 3));
        }
        return cache[a][b];
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.soupServings(4450));
    }
}
