package lc.daily.date2207.Q749;

import java.util.*;

/**
 * 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 *
 * 假设世界由m x n的二维矩阵isInfected组成，isInfected[i][j] == 0表示该区域未感染病毒，
 * 而 isInfected[i][j] == 1表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 *
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。
 * 现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），
 * 且该感染区域对未感染区域的威胁最大且 保证唯一。
 *
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数;
 * 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 *
 * 示例 1：
 * [[0 1 0 0 0 0 0 1]
 *  [0 1 0 0 0 0 0 1]
 *  [0 0 0 0 0 0 0 1]
 *  [0 0 0 0 0 0 0 0]
 * ]
 *
 * 输入: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
 * 输出: 10
 * 解释:一共有两块被病毒感染的区域。
 * 在第一天，添加 5 墙隔离病毒区域的左侧。病毒传播后的状态是:
 * [[0 -1 0 0 0 0 1 1]
 *  [0 -1 0 0 0 0 1 1]
 *  [0  0 0 0 0 0 1 1]
 *  [0  0 0 0 0 0 0 1]
 * ]
 *
 * 第二天，在右侧添加 5 个墙来隔离病毒区域。此时病毒已经被完全控制住了。
 * [[0 -1 0 0 0 0 -1 -1]
 *  [0 -1 0 0 0 0 -1 -1]
 *  [0  0 0 0 0 0 -1 -1]
 *  [0  0 0 0 0 0 0  -1]
 * ]
 *
 * 示例 2：
 * [[1 1 1]       [[-1 -1 -1]
 *  [1 0 1]   ->   [-1  0 -1]
 *  [1 1 1]        [-1 -1 -1]
 * ]              ]
 * 输入: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出: 4
 * 解释: 虽然只保存了一个小区域，但却有四面墙。
 * 注意，防火墙只建立在两个不同区域的共享边界上。
 *
 * 示例3:
 *
 * 输入: isInfected = [[1,1,1,0,0,0,0,0,0],[1,0,1,0,1,1,1,1,1],[1,1,1,0,0,0,0,0,0]]
 * 输出: 13
 * 解释: 在隔离右边感染区域后，隔离左边病毒区域只需要 2 个防火墙。
 *
 * 提示:
 *
 * m == isInfected.length
 * n == isInfected[i].length
 * 1 <= m, n <= 50
 * isInfected[i][j] is either 0 or 1
 * 在整个描述的过程中，总有一个相邻的病毒区域，它将在下一轮 严格地感染更多未受污染的方块
 *
 *
 * */

class Region{
    // 用二维下标映射成整数，存放坐标
    Set<Integer> infectedRegion, uninfectedNeighbors;
    int wallsNeeded;
    Region(){
        infectedRegion = new HashSet<>();
        uninfectedNeighbors = new HashSet<>();
        wallsNeeded = 0;
    }
}

class Solution {
    // 矩阵
    private int[][] isInfected;
    // 矩阵的行列
    private int m,n;
    // 第几天
    private int phase;
    // 按照未感染邻居的size维护一个区域大顶堆
    private PriorityQueue<Region> topRegions;
    // 方向矩阵
    private final int[][] dir = {{-1,0},{0,1},{1,0},{0,-1}};

    public int containVirus(int[][] isInfected) {
        int res = 0;
        this.isInfected = isInfected;
        m = isInfected.length; n = isInfected[0].length;
        phase = 1;
        topRegions = new PriorityQueue<>(
                (a,b)->b.uninfectedNeighbors.size() - a.uninfectedNeighbors.size());
        // 每次更新用下一天表示已访问状态，所以建墙之后需要天数+1才能进行传播
        updateRegions();
        while(!topRegions.isEmpty()){
            Region currentRegion = topRegions.poll();
            res += currentRegion.wallsNeeded;
            buildWalls(currentRegion); // 建墙
            phase ++; // 下一天
            spread(); // 传播
            updateRegions();
        }

        return res;
    }

    private void updateRegions(){
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (isInfected[i][j] == phase){
                    Region region = new Region();
                    findInfectedRegion(region,i,j);
                    if (!region.uninfectedNeighbors.isEmpty()){
                        topRegions.offer(region);
                    }
                }
            }
        }
    }

    private void findInfectedRegion(Region region, int i, int j){
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(i * n + j);
        while(!queue.isEmpty()){
            int curPos = queue.removeFirst();
            int curX = curPos / n, curY = curPos % n;
            // 扫描到0
            if (isInfected[curX][curY] == 0){
                region.uninfectedNeighbors.add(curPos);// 该位置加入区域的未感染邻居
                region.wallsNeeded ++;// 需要围墙数+1
            } else if (isInfected[curX][curY] == phase) {
                isInfected[curX][curY] ++; // 用下一天数标记其为已访问状态
                region.infectedRegion.add(curPos); // 该位置加入区域的感染区
                for (int d = 0; d < 4; d++) {
                    int x = curX + dir[d][0],
                        y = curY + dir[d][1];
                    // 如果邻居在范围内，而且不是被隔离状态，并且是0或者phase，则加入队列
                    if (x >= 0 && x < m && y >= 0 && y < n
                            && isInfected[x][y] <= phase && isInfected[x][y] !=  -1){
                        queue.addLast(x * n + y);
                    }
                }
            }
        }
    }

    private void buildWalls(Region region){
        for(int pos: region.infectedRegion){
            isInfected[pos / n][pos % n] = -1;
        }
    }

    private void spread(){
        while(!topRegions.isEmpty()){
            Region region = topRegions.poll();
            for(int pos: region.uninfectedNeighbors){
                isInfected[pos / n][pos % n] = phase;
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] isInfected = {{0,1,0,0,0,0,0,1},{0,1,0,0,0,0,0,1},{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0}};
        System.out.println(solution.containVirus(isInfected));
    }
}
