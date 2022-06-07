package com.algorithm.chapter4.jianzhi37;

import com.algorithm.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 *
 * 你需要设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。
 * 你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 *
 *
 *
 * 示例：
 *         1
 *        / \
 *       2   3
 *          / \
 *         4   5
 *
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 *
 * */

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));


class Codec {

    private int p;

    private StringBuilder res;

    private void serializeCore(TreeNode node){
        if (node == null) {
            res.append('#');
            res.append(',');
            return;
        }
        res.append(node.val);
        res.append(',');
        serializeCore(node.left);
        serializeCore(node.right);
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        res = new StringBuilder();
        serializeCore(root);
        int lastIdx = res.length() - 1;
        if (res.charAt(lastIdx) == ','){
            res.deleteCharAt(lastIdx);
        }
        return res.toString();
    }

    private TreeNode deserializeCore(String[] strings){
        TreeNode node = null;
        if (!strings[p].equals("#")){
            node  = new TreeNode(Integer.parseInt(strings[p]));
            p ++;
            node.left = deserializeCore(strings);
            node.right = deserializeCore(strings);
        }else{
            p ++;
        }
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strings = data.split(",");
        p = 0;
        return deserializeCore(strings);
    }
}


public class Main {
    public static void main(String[] args) {
        Codec codec = new Codec();

        TreeNode node21 = new TreeNode(4);
        TreeNode node22 = new TreeNode(5);
        TreeNode node11 = new TreeNode(2);
        TreeNode node12 = new TreeNode(3,node21,node22);
        TreeNode root = new TreeNode(1,node11,node12);

        String s = codec.serialize(root);
        System.out.println(s);

        TreeNode newRoot = codec.deserialize(s);
        TreeNode.preorder(newRoot);

    }
}
