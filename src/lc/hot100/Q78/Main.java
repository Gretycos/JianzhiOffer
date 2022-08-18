package lc.hot100.Q78;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 *
 * */

class Solution {
    private List<List<Integer>> res;
    private List<Integer> temp;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        temp = new ArrayList<>();
        res.add(new ArrayList<>());
        dfs(nums,0);
        return res;
    }
    private void dfs(int[] nums, int p){
        if (p == nums.length){
            return;
        }

        for (int i = p; i < nums.length; i++) {
            temp.add(nums[i]);
            res.add(new ArrayList<>(temp));
            dfs(nums,i+1);
            temp.remove(temp.size()-1);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,2,3,4};
        System.out.println(solution.subsets(nums));
    }
}
