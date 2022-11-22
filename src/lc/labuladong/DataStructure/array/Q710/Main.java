package lc.labuladong.DataStructure.array.Q710;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Solution {
    private int bound;
    private Map<Integer,Integer> map;
    private Random random;
    public Solution(int n, int[] blacklist) {
        bound = n - blacklist.length;
        map = new HashMap<>();
        random = new Random();
        for (int black : blacklist) {
            // 仅仅把数字存入map中，方便判断数字在不在黑名单内
            map.put(black,0);
        }
        int last = n - 1;
        for (int black : blacklist) {
            // >= bound的黑名单数就不需要映射
            if (black >= bound) continue;
            // 该索引的数在黑名单里，索引--
            while (map.containsKey(last)) last--;
            map.put(black,last--);
        }
    }

    public int pick() {
        int idx = random.nextInt(bound);
        // 如果idx在黑名单里，就要map.get(idx),否则返回idx
        return map.getOrDefault(idx,idx);
    }
}
public class Main {
    public static void main(String[] args) {
        int[] blacklist = {2,3,5};
        Solution solution = new Solution(7,blacklist);
        System.out.println(solution.pick());
    }
}
