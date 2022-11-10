package lc.daily.date2211.Q864;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 给定一个二维网格grid，其中：
 *
 * '.' 代表一个空房间
 * '#' 代表一堵
 * '@'是起点
 * 小写字母代表钥匙
 * 大写字母代表锁
 * 我们从起点开始出发，一次移动是指向四个基本方向之一行走一个单位空间。
 * 我们不能在网格外面行走，也无法穿过一堵墙。如果途经一个钥匙，我们就把它捡起来。
 * 除非我们手里有对应的钥匙，否则无法通过锁。
 *
 * 假设 k为 钥匙/锁 的个数，且满足1 <= k<= 6，字母表中的前 k个字母在网格中都有自己对应的一个小写和一个大写字母。
 * 换言之，每个锁有唯一对应的钥匙，每个钥匙也有唯一对应的锁。
 * 另外，代表钥匙和锁的字母互为大小写并按字母顺序排列。
 *
 * 返回获取所有钥匙所需要的移动的最少次数。如果无法获取所有钥匙，返回-1。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：grid = [
 *              "@.a.#",
 *              "###.#",
 *              "b.A.B"]
 * 输出：8
 * 解释：目标是获得所有钥匙，而不是打开所有锁。
 *
 *
 * 示例 2：
 *
 *
 * 输入：grid = [
 *          "@..aA",
 *          "..B#.",
 *          "....b"]
 * 输出：6
 *
 *
 * 示例 3:
 *
 *
 * 输入: grid = ["@Aa"]
 * 输出: -1
 *
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 30
 * grid[i][j]只含有'.','#','@','a'-'f'以及'A'-'F'
 * 钥匙的数目范围是[1, 6]
 * 每个钥匙都对应一个 不同 的字母
 * 每个钥匙正好打开一个对应的锁
 *
 * */

class Solution {
    private final int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length();
        // 钥匙的数量
        int keys = 0;
        // 起点的位置
        int startX = 0, startY = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                // 寻找起点
                if (c == '@'){
                    startX = i;
                    startY = j;
                }else if (Character.isLowerCase(c)){
                    keys++;
                }
            }
        }

        // BFS队列
        Deque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{startX,startY,0});
        // 访问数组
        boolean[][][] visited = new boolean[m][n][1 << keys];
        visited[startX][startY][0] = true;
        // 获取完所有钥匙所需的步数
        int step = 0;
        while (!q.isEmpty()){
            // 层序遍历
            for (int size = q.size(); size > 0; size--){
                int[] curState = q.removeFirst();
                int x = curState[0], y = curState[1], state = curState[2];
                // 找到所有钥匙
                if (state == (1 << keys) - 1){
                    return step;
                }
                // 四个方向
                for(int d = 0; d < 4; d++){
                    int nX = x + dir[d][0], nY = y + dir[d][1];
                    if (nX >= 0 && nX < m && nY >= 0 && nY < n){
                        char c = grid[nX].charAt(nY);
                        // 如果是墙 或者 遇到了锁但没有相应的钥匙
                        if (c == '#'
                                || (Character.isUpperCase(c) && ((state >> (c - 'A')) & 1) == 0)){
                            continue;
                        }
                        int nState = state;
                        // 更新钥匙状态
                        if (Character.isLowerCase(c)){
                            nState |= 1 << (c - 'a');
                        }
                        // 如果该位置状态未访问过
                        if (!visited[nX][nY][nState]){
                            visited[nX][nY][nState] = true;
                            q.addLast(new int[]{nX,nY,nState});
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
public class Main {
}
