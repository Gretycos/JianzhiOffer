package lc.daily.date2302.Q1604;

import java.util.*;

class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < keyName.length; i++) {
            String[] time = keyTime[i].split(":");
            int hour = Integer.parseInt(time[0]), minute = Integer.parseInt(time[1]);
            map.computeIfAbsent(keyName[i], key -> new ArrayList<>()).add(new int[]{hour,minute});
        }
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, List<int[]>> entry : map.entrySet()) {
            String name = entry.getKey();
            List<int[]> time = entry.getValue();
            time.sort((t1, t2) -> t1[0] - t2[0] != 0 ? t1[0] - t2[0] : t1[1] - t2[1]);
            for (int i = 0; i < time.size() - 2; i++) {
                if (check(time.get(i), time.get(i+2))){
                    res.add(name);
                    break;
                }
            }
        }
        res.sort((name1, name2) -> name1.compareTo(name2));
        return res;
    }

    private boolean check(int[] time1, int[] time2){
        int hour1 = time1[0], hour2 = time2[0];
        int minute1 = time1[1], minute2 = time2[1];
        if (hour2 - hour1 > 1) return false;
        return (hour2 - hour1) * 60 + minute2 - minute1 <= 60;
    }
}

public class Main {
    public static void main(String[] args) {
        String[] keyName = {"a","a","a","a","a","b","b","b","b","b","b"};
        String[] keyTime = {"04:48","23:53","06:36","07:45","12:16","00:52","10:59","17:16","00:36","01:26","22:42"};
        Solution solution = new Solution();
        System.out.println(solution.alertNames(keyName, keyTime));
    }
}
