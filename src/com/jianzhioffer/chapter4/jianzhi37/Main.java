package com.jianzhioffer.chapter4.jianzhi37;

import com.TreeNode;

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
    // 反序列化的字符串数组
    private String[] strings;
    // 反序列化的字符串数组索引
    private int p;

    // 序列化后的字符串
    private StringBuilder sb;
    // 序列化的树根
    private TreeNode root;

    private void serializeCore(TreeNode node){
        if (node == null) {
            if (node != root){
                sb.append(",");
            }
            sb.append("#");
            return;
        }
        // 前序遍历
        // 根
        if (node != root){
            sb.append(",");
        }
        sb.append(node.val);
        // 左
        serializeCore(node.left);
        // 右
        serializeCore(node.right);
    }


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        this.root = root;
        serializeCore(root);
        return sb.toString();
    }

    private TreeNode deserializeCore(){
        TreeNode node = null;
        // 不是空节点
        if (!strings[p].equals("#")){
            node  = new TreeNode(Integer.parseInt(strings[p]));
            p ++;
            node.left = deserializeCore();
            node.right = deserializeCore();
        }else{
        // 空节点
            p ++;
        }
        return node;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        strings = data.split(",");
        p = 0;
        return deserializeCore();
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
