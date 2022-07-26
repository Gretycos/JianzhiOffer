package com.daily.Q1206;

/**
 * 不使用任何库函数，设计一个 跳表 。
 *
 * 跳表 是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。
 * 跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 *
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。
 * 跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 *
 * 在本题中，你的设计应该要包含这些函数：
 *
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num):插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果num不存在，直接返回false. 如果存在多个num，删除其中任意一个即可。
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 *
 *
 * 示例 1:
 *
 *
 * 输入
 * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
 * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
 * 输出
 * [null, null, null, null, false, null, true, false, true, false]
 *
 * 解释
 * Skiplist skiplist = new Skiplist();
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 *
 *
 * 提示:
 *
 * 0 <= num, target <= 2 * 10^4
 * 调用search, add, erase操作次数不大于5 * 10^4
 *
 **/

class Skiplist {
    private class Node{
        int val;
        Node[] next;
        public Node(int val, int size){
            this.val = val;
            next = new Node[size];
        }
    }
    // 随机数因子
    private final double P = 0.25;
    // 最大层数
    private final int MAX_LEVEL = 32;
    // 头节点
    Node head;
    // 当前最高级数
    int currentLevel;

    public Skiplist() {
        // 开始最高级是1级
        currentLevel = 1;
        // 头节点有MAX_LEVEL级
        head = new Node(-1,MAX_LEVEL);
    }

    public boolean search(int target) {
        Node currentNode = head;
        for (int level = currentLevel - 1; level >= 0; level--){
            currentNode = findClosest(currentNode,level,target);
            // 下一个节点一定是 >= target 的
            if (currentNode.next[level] != null && currentNode.next[level].val == target){
                return true;
            }
        }
        return false;
    }

    public void add(int num) {
        // 获得一个随机奇数
        int randomLevel = randomLevel();
        Node currentNode = head,
             newNode = new Node(num,randomLevel); // 新节点
        // 自上而下查询可插入点
        for (int level = currentLevel - 1; level >= 0; level--){
            currentNode = findClosest(currentNode,level,num);
            if (level < randomLevel){
                // 下一个节点为空，直接插入
                if (currentNode.next[level] == null){
                    currentNode.next[level] = newNode;
                // 否则，按一般插入方式插入
                }else{
                    Node t = currentNode.next[level];
                    currentNode.next[level] = newNode;
                    newNode.next[level] = t;
                }
            }
        }
        // 随机级数比当前等级大，把超出级数的头节点指向新节点
        if (randomLevel > currentLevel){
            for (int level = currentLevel; level < randomLevel; level++){
                head.next[level] = newNode;
            }
            currentLevel = randomLevel;
        }
    }

    public boolean erase(int num) {
        boolean flag = false;
        Node currentNode = head;
        for (int level = currentLevel - 1; level >= 0; level--){
            currentNode = findClosest(currentNode,level,num);
            if (currentNode.next[level] != null && currentNode.next[level].val == num){
                currentNode.next[level] = currentNode.next[level].next[level];
                flag = true;
                continue;
            }
        }
        return flag;
    }

    private int randomLevel(){
        int level = 1;
        while(Math.random() < P && level < MAX_LEVEL){
            level ++;
        }
        return level;
    }

    // 在第level层寻找最后一个值 < target 的节点
    private Node findClosest(Node node, int level, int target){
        while(node.next[level] != null && node.next[level].val < target){
            node = node.next[level];
        }
        return node;
    }
}

public class Main {
}
