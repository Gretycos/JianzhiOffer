package lc.labuladong.Others.Classic.Q659;

import java.util.*;

class Solution {
    private Map<Integer, Integer> freq, need;

    public boolean isPossible(int[] nums) {
        freq = new HashMap<>();
        need = new HashMap<>();
        // 统计 nums 中元素的频率
        for (int num : nums) {
            // compute重映射
            freq.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                // 已经被用到其他子序列中
                continue;
            }

            if (need.getOrDefault(num, 0) > 0) {
                // num 可以接到之前的某个序列后面
                freq.compute(num, (k, v) -> v - 1);
                // 对 num 的需求减一
                need.compute(num, (k, v) -> v - 1);
                // 对 num + 1 的需求加一
                need.compute(num + 1, (k, v) -> v == null ? 1 : v + 1);
            } else if (freq.getOrDefault(num + 1, 0) > 0
                    && freq.getOrDefault(num + 2, 0) > 0) {
                // 将 num 作为开头，新建一个长度为 3 的子序列 [num,num+1,num+2]
                freq.compute(num, (k, v) -> v - 1);
                freq.compute(num + 1, (k, v) -> v - 1);
                freq.compute(num + 2, (k, v) -> v - 1);
                // 对 num + 3 的需求加一
                need.compute(num + 3, (k, v) -> v == null ? 1 : v + 1);
            } else {
                return false;
            }
        }
        return true;
    }
}

class Solution2 {
    private Map<Integer, Integer> freq;
    private Map<Integer, LinkedList<List<Integer>>> need;

    public boolean isPossible(int[] nums) {
        freq = new HashMap<>();
        need = new HashMap<>();
        // 统计 nums 中元素的频率
        for (int num : nums) {
            // compute重映射
            freq.compute(num, (k, v) -> v == null ? 1 : v + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                // 已经被用到其他子序列中
                continue;
            }

            if (need.containsKey(num) && need.get(num).size() > 0) {
                // num 可以接到之前的某个序列后面
                freq.compute(num, (k, v) -> v - 1);
                // 取需要num的子序列
                List<Integer> seq = need.get(num).removeLast();
                seq.add(num); // 注意，如果是asList生成的List没有实现add和remove方法
                // 这个子序列的需求变成了 num + 1
                need.computeIfAbsent(num+1,k -> new LinkedList<>()).add(seq);
            } else if (freq.getOrDefault(num + 1, 0) > 0
                    && freq.getOrDefault(num + 2, 0) > 0) {
                // 将 num 作为开头，新建一个长度为 3 的子序列 [num,num+1,num+2]
                freq.compute(num, (k, v) -> v - 1);
                freq.compute(num + 1, (k, v) -> v - 1);
                freq.compute(num + 2, (k, v) -> v - 1);
                List<Integer> seq = new LinkedList<>();
                for (int i = num; i <= num+2; i++) {
                    seq.add(i);
                }
                // 对 num + 3 的需求加一
                need.computeIfAbsent(num+3,k -> new LinkedList<>())
                        .add(seq);
            } else {
                return false;
            }
        }

        for (LinkedList<List<Integer>> list: need.values()) {
            for (List<Integer> seq : list) {
                System.out.println(seq);
            }
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {3,4,5,5,6,6,7,7,8,9};
        solution.isPossible(nums);
    }
}
