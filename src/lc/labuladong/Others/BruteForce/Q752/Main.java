package lc.labuladong.Others.BruteForce.Q752;

import java.util.*;

class Solution {
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        Queue<String> q = new ArrayDeque<>();
        q.add("0000");

        int step = 0;
        while (!q.isEmpty()){
            int size = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < size; i++) {
                String cur = q.remove();

                // 跳过死亡密码
                if (visited.contains(cur)){
                    continue;
                }
                /* 判断是否到达终点 */
                if (cur.equals(target)){
                    return step;
                }

                visited.add(cur);

                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)){
                        q.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)){
                        q.add(down);
                    }
                }
            }
            step++;
        }

        return -1;
    }

    public int openLock2(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();
        for (String deadend : deadends) {
            deads.add(deadend);
        }

        Set<String> visited = new HashSet<>();
        // 用集合不用队列，判断元素是否存在
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();

        int step = 0;
        q1.add("0000");
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()){
            Set<String> temp = new HashSet<>();

            for (String cur : q1) {
                if (deads.contains(cur)){
                    continue;
                }
                if (q2.contains(cur)){
                    return step;
                }

                visited.add(cur);

                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)){
                        temp.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)){
                        temp.add(down);
                    }
                }
            }
            step ++;
            q1 = q2;
            q2 = temp;
        }
        return -1;
    }


    private String minusOne(String s, int j){
        char[] chs = s.toCharArray();
        if (chs[j] == '0'){
            chs[j] = '9';
        } else {
            chs[j] -= 1;
        }
        return new String(chs);
    }

    private String plusOne(String s, int j){
        char[] chs = s.toCharArray();
        if (chs[j] == '9'){
            chs[j] = '0';
        } else {
            chs[j] += 1;
        }
        return new String(chs);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] deadends = {"0201","0101","0102","1212","2002"};
        String target = "0202";
        System.out.println(solution.openLock(deadends, target));
    }
}
