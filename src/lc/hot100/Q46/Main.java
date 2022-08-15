package lc.hot100.Q46;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出：[[1]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * */

class Solution {
    private int[] nums;
    private List<List<Integer>> res;
    private List<Integer> temp;
    private boolean[] isVisited;
    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        res = new ArrayList<>();
        temp = new ArrayList<>();
        isVisited = new boolean[nums.length];
        dfs();
        return res;
    }

    private void dfs(){
        if (temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if (!isVisited[i]){
                isVisited[i] = true;
                temp.add(nums[i]);
                dfs();
                temp.remove(temp.size()-1);
                isVisited[i] = false;
            }
        }
    }

}
public class Main {
}
