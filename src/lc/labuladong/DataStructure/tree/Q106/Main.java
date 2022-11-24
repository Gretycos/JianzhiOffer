package lc.labuladong.DataStructure.tree.Q106;

import lc.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer,Integer> valToIdx;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        valToIdx = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIdx.put(inorder[i],i);
        }
        return build(postorder, 0, postorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int postStart, int postEnd,
                           int[] inorder, int inStart, int inEnd){
        if (postStart > postEnd){
            return null;
        }
        int rootVal = postorder[postEnd];
        int idx = valToIdx.get(rootVal);
        int leftSize = idx - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(postorder, postStart, postStart + leftSize - 1,
                inorder, inStart, idx);
        root.right = build(postorder, postStart + leftSize, postEnd - 1,
                inorder, idx + 1, inEnd);
        return root;
    }
}
public class Main {
}
