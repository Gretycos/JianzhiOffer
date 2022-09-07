package lc.hot100.Q51to75.Q146;

import java.util.HashMap;
import java.util.Map;

/**
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
 * 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 *
 *
 * 提示：
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 10^5
 * 最多调用 2 * 10^5 次 get 和 put
 *
 * */
class Node{
    int key;
    int value;
    Node pre;
    Node next;
    Node(int K, int V){key = K; value = V;}
}
class LRUCache {
    private Map<Integer, Node> map; // 哈希表
    private int size, max;
    private Node head, tail; // 双向链表

    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = 0;
        max = capacity;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        moveToHead(node); // get成功一次之后，把节点移动到链表头
        return node.value;
    }

    private void moveToHead(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;

        node.next = head.next;
        node.pre = head;

        node.next.pre = node;
        head.next = node;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null){ // 如果节点存在，更新节点，并移动到链表头
            node.value = value;
            moveToHead(node);
        }else{
            node = new Node(key,value); // 节点不存在，新建
            if (size == max){ // 容量满了，需要删除尾节点
                map.remove(tail.pre.key);
                deleteTail();
                size --;
            }
            insertHead(node); // 在头部插入新节点
            size ++;
            map.put(key,node); // 放入哈希表
        }
    }

    private void deleteTail(){
        Node node = tail.pre;
        node.pre.next = tail;
        tail.pre = node.pre;
        node.next = null;
        node.pre = null;
    }

    private void insertHead(Node node){
        node.pre = head;
        node.next = head.next;

        node.next.pre = node;
        head.next = node;

    }
}
public class Main {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.get(1);
        lruCache.put(3,3);
        lruCache.get(2);
        lruCache.put(4,4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}
