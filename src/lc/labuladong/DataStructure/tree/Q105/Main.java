package lc.labuladong.DataStructure.tree.Q105;

import lc.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer,Integer> valToIndex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        valToIndex = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            valToIndex.put(inorder[i],i);
        }
        return build(preorder, 0, preorder.length-1,
                        inorder, 0, inorder.length - 1);
    }
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd){
        if (preStart > preEnd){
            return null;
        }
        int rootVal = preorder[preStart];
        int index = valToIndex.get(rootVal);

        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                            inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                            inorder, index + 1, inEnd);
        return root;
    }
}
public class Main {
}
