package lc.daily.date2212.Q1687;

import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        int n = boxes.length;
        int[] count = new int[n];
        long[] preSumW = new long[n + 1];
        int[] preSumCount = new int[n + 1];

        for (int i = 1; i < n; i++) {
            count[i] = boxes[i][0] == boxes[i - 1][0] ? 0 : 1;
        }

        for (int i = 1; i <= n; i++) {
            preSumW[i] = preSumW[i - 1] + boxes[i - 1][1];
            preSumCount[i] = preSumCount[i - 1] + count[i - 1];
        }

        Deque<Integer> q = new LinkedList<>(); // 不理解
        q.addLast(0); // 不理解

        int[] f = new int[n + 1];
        int[] g = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            while (i - q.getFirst() > maxBoxes || preSumW[i] - preSumW[q.getFirst()] > maxWeight){
                q.removeFirst();
            }

            f[i] = g[q.getFirst()] + preSumCount[i] + 2;

            if (i != n){
                g[i] = f[i] - preSumCount[i+1];
                while (!q.isEmpty() && g[i] <= g[q.getLast()]){
                    q.removeLast();
                }
                q.addLast(i);
            }
        }
        return f[n];
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] boxes = {
                {2,4},
                {2,5},
                {3,1},
                {3,2},
                {3,7},
                {3,1},
                {4,4},
                {1,3},
                {5,2}
        };
        int portsCount = 5, maxBoxes = 5, maxWeight = 7;
        System.out.println(solution.boxDelivering(boxes,portsCount,maxBoxes,maxWeight));
    }
}
