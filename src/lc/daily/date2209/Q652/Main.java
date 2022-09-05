package lc.daily.date2209.Q652;

import lc.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,2,3,4,null,2,4,null,null,4]
 * 输出：[[2,4],[4]]
 *
 *
 * 示例 2：
 *
 *
 *
 * 输入：root = [2,1,1]
 * 输出：[[1]]
 *
 *
 * 示例 3：
 *
 *
 *
 * 输入：root = [2,2,2,3,null,3,null]
 * 输出：[[2,3],[3]]
 *
 *
 * 提示：
 *
 * 树中的结点数在[1,10^4]范围内。
 * -200 <= Node.val <= 200
 *
 * */

class Solution {
    private List<TreeNode> res;
    private Map<String,Integer> map;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        map = new HashMap<>();
        dfs(root);
        return res;
    }
    private String dfs(TreeNode node){
        if (node == null){
            return "#";
        }
        String key = new StringBuilder()
                .append(node.val).append(",")
                .append(dfs(node.left)).append(",")
                .append(dfs(node.right)).append(",").toString(); // 序列化
        // 把出现次数大于1的节点加入结果
        int value = map.getOrDefault(key,0);
        if (value == 1){
            res.add(node);
        }
        map.put(key,value + 1);
        return key;
    }
}
public class Main {
}
