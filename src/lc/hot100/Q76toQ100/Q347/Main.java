package lc.hot100.Q76toQ100.Q347;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 *
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 *
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^5
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的
 *
 *
 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n是数组大小。
 *
 * */

class Solution {
    // 桶排序
    // T: O(n)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        // 按频率对列表标号
        // 每个频率建立一个桶
        // 把频率相同的元素放进一个桶
        List<Integer>[] list = new List[nums.length+1];
        for (int key : map.keySet()) {
            int f = map.get(key);
            if (list[f] == null){
                list[f] = new ArrayList<>();
            }
            list[f].add(key);
        }
        int[] res = new int[k];
        // 倒序遍历列表
        for (int i = list.length - 1,idx = 0; i >= 0 && idx < k; i--) {
            if (list[i] != null){
                for (int key : list[i]) {
                    res[idx++] = key;
                }
            }
        }
        return res;
    }

    // 快排
    // T: O(n + klogk)
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((o1, o2) -> o2.getValue() - o1.getValue());
        int[] res = new int[k];
        for (int i = 0; i < k; i++){
            res[i] = list.get(i).getKey();
        }
        return res;
    }

    // 堆
    // T: O(n + klogk)
    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0)+1);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> q = new PriorityQueue<>((o1,o2)->o1.getValue()-o2.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (q.size() == k){
                // 替换较小的元素
                if (q.peek().getValue() < entry.getValue()){
                    q.poll();
                }else{
                    continue;
                }
            }
            q.offer(entry);
        }

        int[] res = new int[k];
        for (int i = k-1; i >= 0; i--) {
            res[i] = q.poll().getKey();
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,1,1,2,2,3};
        solution.topKFrequent(nums,2);
    }
}
