package lc.hot100.Q51to75.Q297;

import lc.TreeNode;

class Codec {

    private StringBuilder SB;
    private String[] nodes;
    private int n;
    private int p;
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        SB = new StringBuilder();
        preOrder(root);
        SB.deleteCharAt(SB.length()-1);
        return SB.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        nodes = data.split(",");
        n = nodes.length;
        p = 0;
        return build();
    }

    private void preOrder(TreeNode node){
        if (node == null){
            SB.append("#,");
            return;
        }
        SB.append(node.val).append(",");
        preOrder(node.left);
        preOrder(node.right);
    }

    private TreeNode build(){
        TreeNode node = null;
        if (p < n){
            String val = nodes[p];
            if (!val.equals("#")){
                node = new TreeNode(Integer.parseInt(val));
                p++;
                node.left = build();
                node.right = build();
            }else{
                p++;
            }
        }
        return node;
    }
}
public class Main {
}
