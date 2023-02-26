package lc.labuladong.Others.BruteForce.Q698;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {


    // 以数字为视角
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) return false;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) return false;
        // 升序排序，但是倒序遍历
        // 相当于降序排序
        // 先用更大的元素去填 容易触发剪枝条件
        Arrays.sort(nums);
        // k 个桶（集合），记录每个桶装的数字之和
        int[] bucket = new int[k];
        // 理论上每个桶（集合）中数字的和
        int target = sum / k;
        // 穷举，看看 nums 是否能划分成 k 个和为 target 的子集
        return backtrack(nums, nums.length - 1, bucket, target);
    }

    private boolean backtrack(int[] nums, int index, int[] bucket, int target) {
        if (index < 0) {
            // 实际上所有球已经按要求装入所有桶, 不需要重新遍历一次所有数组是否满足条件
            return true;
        }

        for (int i = 0; i < bucket.length; i++) {
            // 如果当前桶和上一个桶内的元素和相等，则跳过
            // 原因：如果元素和相等，那么 nums[index] 选择上一个桶和选择当前桶可以得到的结果是一致的
            if (i > 0 && bucket[i] == bucket[i-1]){
                continue;
            }
            // 装满了
            if (bucket[i] + nums[index] > target) {
                continue;
            }
            // 选择
            bucket[i] += nums[index];
            // 下一层
            if (backtrack(nums, index - 1, bucket, target)) {
                return true;
            }
            // 撤销
            bucket[i] -= nums[index];
        }
        return false;
    }

    private Map<Integer, Boolean> memo;
    // 桶视角
    public boolean canPartitionKSubsets2(int[] nums, int k) {
        // 排除一些基本情况
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;

        memo = new HashMap<>();
        int used = 0; // 使用位图技巧
        int target = sum / k;
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack(k, 0, nums, 0, used, target);
    }

    private boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        // base case
        if (k == 0) {
            // 所有桶都被装满了，而且 nums 一定全部用完了
            return true;
        }
        if (bucket == target) {
            // 装满了当前桶，递归穷举下一个桶的选择
            // 让下一个桶从 nums[0] 开始选数字
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            // 缓存结果
            memo.put(used, res);
            return res;
        }
        if (memo.containsKey(used)) {
            // 避免冗余计算
            return memo.get(used);
        }
        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (((used >> i) & 1) == 1) { // 判断第 i 位是否是 1
                // nums[i] 已经被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target) {
                continue;
            }
            // 做选择
            used |= 1 << i; // 将第 i 位置为 1
            bucket += nums[i];
            // 递归穷举下一个数字是否装入当前桶
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            // 撤销选择
            used ^= 1 << i; // 使用异或运算将第 i 位恢复 0
            bucket -= nums[i];
        }

        return false;
    }
}
public class Main {
}
