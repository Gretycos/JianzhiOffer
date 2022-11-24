package lc.labuladong.DataStructure.tree.Q654;

import lc.TreeNode;

class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums,0,nums.length-1);
    }

    private TreeNode construct(int[] nums, int start, int end){
        if (start > end){
            return null;
        }
        // 找到数组中最大值和对应索引
        int maxIdx = start, max = nums[maxIdx];
        for (int i = start; i <= end; i++) {
            if (nums[i] > max){
                max = nums[i];
                maxIdx = i;
            }
        }
        // 构造根节点
        TreeNode root = new TreeNode(max);
        // 递归构造左右子树
        root.left = construct(nums,start,maxIdx-1);
        root.right = construct(nums,maxIdx+1,end);
        return root;
    }
}
public class Main {
}
