package com.daily.Q558;


/**
 * 二进制矩阵中的所有元素不是 0 就是 1 。
 *
 * 给你两个四叉树，quadTree1 和 quadTree2。其中 quadTree1 表示一个 n * n 二进制矩阵，
 * 而 quadTree2 表示另一个 n * n 二进制矩阵。
 *
 * 请你返回一个表示 n * n 二进制矩阵的四叉树，
 * 它是 quadTree1 和 quadTree2 所表示的两个二进制矩阵进行 按位逻辑或运算 的结果。
 *
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 *
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 *
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 *
 * 我们可以按以下步骤为二维区域构建四叉树：
 *
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，
 * 并将四个子节点都设为 Null 然后停止。
 * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 *
 * 四叉树格式：
 *
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 *
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 *
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表[isLeaf, val] 中的值为 1 ；
 * 如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 *
 *
 *
 * */
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};

class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf){
            if (quadTree1.val){
                return new Node(true,true,
                        null,null,null,null);
            }
            return new Node(quadTree2.val, quadTree2.isLeaf,
                    quadTree2.topLeft,quadTree2.topRight,quadTree2.bottomLeft,quadTree2.bottomRight);
        }
        if (quadTree2.isLeaf){
            // 保证前一个参数为叶节点
            return intersect(quadTree2,quadTree1);
        }

        // 两个都不是叶节点
        Node child1 = intersect(quadTree1.topLeft,quadTree2.topLeft),
             child2 = intersect(quadTree1.topRight,quadTree2.topRight),
             child3 = intersect(quadTree1.bottomLeft,quadTree2.bottomLeft),
             child4 = intersect(quadTree1.bottomRight,quadTree2.bottomRight);
        // 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，
        // 将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
        if (child1.isLeaf && child2.isLeaf && child3.isLeaf && child4.isLeaf
                && child1.val == child2.val && child3.val == child4.val && child1.val == child3.val){
            return new Node(child1.val,true,
                    null,null,null,null);
        }
        // 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后将当前网格划分为四个子网格
        return new Node(false,false,child1,child2,child3,child4);
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println((true == false == true == false));
    }
}
