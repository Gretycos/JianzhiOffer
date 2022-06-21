package com.jianzhioffer.chapter4.jianzhi33;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。
 * 假设输入的数组的任意两个数字都互不相同。
 *
 *
 *
 * 参考以下这颗二叉搜索树：
 *
 *      5
 *     / \
 *    2   6
 *   / \
 *  1   3
 * 示例 1：
 *
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 *
 * 输入: [1,3,2,6,5]
 * 输出: true
 *
 *
 * 提示：
 *
 * 数组长度 <= 1000
 *
 * */

class Solution {

    private boolean verify(int[] postorder, int start, int end){
        if (start >= end){
            return true;
        }

        int root = postorder[end];

        int i = start;
        while (i < end && postorder[i] < root){
            i++;
        }

        for (int j = i; j < end ; j++) {
            if (postorder[j] < root){
                return false;
            }
        }

        return verify(postorder,start,i-1) && verify(postorder,i,end-1);
    }

    public boolean verifyPostorder(int[] postorder) {
        if (postorder.length == 0 || postorder.length == 1){
            return true;
        }
        return verify(postorder,0,postorder.length-1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] postorder = {1,2,5,10,6,9,4,3};
        System.out.println(solution.verifyPostorder(postorder));
    }
}
