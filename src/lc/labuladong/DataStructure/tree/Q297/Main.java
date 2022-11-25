package lc.labuladong.DataStructure.tree.Q297;

import lc.TreeNode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

// 前序
class Codec {
    private StringBuilder sb;
    private final String SEP = ",";
    private final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        _serialize(root);
        return sb.toString();
    }

    private void _serialize(TreeNode root){
        if (root == null){
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        _serialize(root.left);
        _serialize(root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) { // 末尾的空字符不会包含在结果集
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    private TreeNode deserialize(Deque<String> nodes){
        if (nodes.isEmpty()){
            return null;
        }
        String first = nodes.removeFirst();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}

// 后序
class Codec1 {
    private StringBuilder sb;
    private final String SEP = ",";
    private final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        sb = new StringBuilder();
        _serialize(root);
        return sb.toString();
    }

    private void _serialize(TreeNode root){
        if (root == null){
            sb.append(NULL).append(SEP);
            return;
        }
        _serialize(root.left);
        _serialize(root.right);
        sb.append(root.val).append(SEP);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) { // 末尾的空字符不会包含在结果集
            nodes.addLast(s);
        }
        return deserialize(nodes);
    }

    private TreeNode deserialize(Deque<String> nodes){
        if (nodes.isEmpty()){
            return null;
        }
        String first = nodes.removeLast();
        if (first.equals(NULL)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(first));
        root.right = deserialize(nodes);
        root.left = deserialize(nodes);
        return root;
    }
}

// 层序
class Codec2 {
    private StringBuilder sb;
    private final String SEP = ",";
    private final String NULL = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null){
            return "";
        }
        sb = new StringBuilder();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()){
            TreeNode cur = queue.removeFirst();
            if (cur == null){
                sb.append(NULL).append(SEP);
                continue;
            }
            sb.append(cur.val).append(SEP);
            // 与之前层序遍历不同的是，空节点也要进队列
            queue.addLast(cur.left);
            queue.addLast(cur.right);
        }
        return sb.toString();
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.isEmpty()) return null;
        String[] nodes = data.split(SEP);
        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);

        for (int i = 1; i < nodes.length;) {
            TreeNode parent = queue.removeFirst();
            String left = nodes[i++];
            if (!left.equals(NULL)){
                parent.left = new TreeNode(Integer.parseInt(left));
                queue.addLast(parent.left);
            }else{
                parent.left = null;
            }
            String right = nodes[i++];
            if (!right.equals(NULL)){
                parent.right = new TreeNode(Integer.parseInt(right));
                queue.addLast(parent.right);
            }else{
                parent.right = null;
            }
        }

        return root;
    }

}


public class Main {
    public static void main(String[] args) {
        String s = "1,2,#,#,3,4,#,#,5,#,#,";
        System.out.println(Arrays.toString(s.split(",",0)));
    }
}
