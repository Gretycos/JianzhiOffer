package lc.labuladong.DataStructure.tree.Q889;

import lc.TreeNode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer,Integer> valToIdx;
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        valToIdx = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            valToIdx.put(postorder[i],i);
        }
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] postorder, int postStart, int postEnd){
        if (preStart > preEnd){
            return null;
        }
        if (preStart == preEnd){
            return new TreeNode(preorder[preStart]);
        }

        int rootVal = preorder[preStart];
        int leftRootVal = preorder[preStart + 1];
        int idx = valToIdx.get(leftRootVal);
        int leftSize = idx - postStart + 1;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, idx);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, idx + 1, postEnd - 1);
        return root;
    }
}
public class Main {
}
