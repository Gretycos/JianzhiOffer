package lc.labuladong.DataStructure.tree.Q652;

import lc.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    private List<TreeNode> res;
    private Map<String,Integer> cache;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new LinkedList<>();
        cache = new HashMap<>();
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root){
        if (root == null){
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);
        String subTree = left + "," + right + "," + root.val;
        int freq = cache.getOrDefault(subTree, 0);
        // 出现重复的时候，只会加一次进结果集
        if (freq == 1){
            res.add(root);
        }
        cache.put(subTree, freq + 1);
        return subTree;
    }

}
public class Main {
}
