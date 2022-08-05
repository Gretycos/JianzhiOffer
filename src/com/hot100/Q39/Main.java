package com.hot100.Q39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
 * 找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 *
 *
 *
 * 示例1：
 *
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 * 示例2：
 *
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 * 示例 3：
 *
 * 输入: candidates = [2], target = 1
 * 输出: []
 *
 *
 * 提示：
 *
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 *
 * */
class Solution {
    private int[] candidates;
    private List<List<Integer>> res;
    private List<Integer> temp;
    private int target;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        this.candidates = candidates;
        res = new ArrayList<>();
        temp = new ArrayList<>();
        this.target = target;
        dfs(0,0);
        return res;
    }

    void dfs(int sum, int start){
        if (sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }
        // 通过开头限定去重
        for (int i = start; i < candidates.length; i++) {
            if (sum + candidates[i] <= target) {
                temp.add(candidates[i]);
                dfs(sum + candidates[i],i);
                temp.remove(temp.size() - 1);
            } else {
                return;
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] candidates = {2,3,6,7};
        int target = 7;
        System.out.println(solution.combinationSum(candidates,target));
    }
}
