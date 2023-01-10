package lc.daily.date2301.Q753;

import java.util.HashSet;
import java.util.Set;

class Solution {
    private Set<Integer> visit;
    private StringBuilder sb;
    private int highest;
    private int k;

    public String crackSafe(int n, int k) {
        highest = (int) Math.pow(10, n-1);
        this.k = k;
        visit = new HashSet<>();
        sb = new StringBuilder();
        dfs(0);
        // 最后一个节点必须能够回到出发原点，即全0
        sb.append("0".repeat(n-1));
        return sb.toString();
    }

    private void dfs(int u){
        for (int x = 0; x < k; x++) {
            int v = u * 10 + x; // 连接下一位
            if (!visit.contains(v)){
                visit.add(v);
                dfs(v % highest); // 去掉第一位
                sb.append(x);
            }
        }
    }
}

public class Main {
}
