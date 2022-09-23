package lc.daily.date2209.Q707;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。
 * 单链表中的节点应该具有两个属性：val和next。val是当前节点的值，next是指向下一个节点的指针/引用。
 * 如果要使用双向链表，则还需要一个属性prev以指示链表中的上一个节点。
 * 假设链表中的所有节点都是 0-index 的。
 *
 * 在链表类中实现这些功能：
 *
 * get(index)：获取链表中第index个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为val的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第index个节点之前添加值为val 的节点。
 *                  如果index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。
 *                  如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引index 有效，则删除链表中的第index 个节点。
 *
 *
 * 示例：
 *
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *
 *
 * 提示：
 *
 * 所有val值都在[1, 1000]之内。
 * 操作次数将在[1, 1000]之内。
 * 请不要使用内置的 LinkedList 库。
 *
 * */

class MyLinkedList {
    private static class Node{
        int val;
        Node next;
        Node(int val){this.val = val;}
    }

    private Node[] list;
    private int size = 0;
    private int capacity = 16;
    private static final double THRESHOLD = 0.75; // 阈值

    public MyLinkedList() {
        list = new Node[capacity];
    }

    public int get(int index) {
        if (index >= size){
            return -1;
        }
        return list[index].val;
    }

    public void addAtHead(int val) {
        checkSize();
        System.arraycopy(list,0,list,1,size);
        list[0] = new Node(val);
        list[0].next = list[1];
        size ++;
    }

    public void addAtTail(int val) {
        checkSize();
        list[size] = new Node(val);
        if (size > 0){
            list[size - 1].next = list[size];
        }
        size ++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        checkSize();
        System.arraycopy(list,index,list,index+1,size - index);
        list[index] = new Node(val);
        list[index-1].next = list[index];
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) return;
        if (index > 0){
            list[index - 1].next = list[index + 1];
        }
        list[index].next = null;
        System.arraycopy(list,index+1,list,index,size-1-index);
        size --;
    }

    private void grow(){
        capacity *= 2;
        Node[] newList = new Node[capacity];
        System.arraycopy(list,0,newList,0,size);
        list = newList;
    }

    private void checkSize(){
        if (size + 1 > (int)(capacity * THRESHOLD)){
            grow();
        }
    }
}
public class Main {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3,0);
        myLinkedList.deleteAtIndex(2);
        myLinkedList.addAtHead(6);
        myLinkedList.addAtTail(4);
        System.out.println(myLinkedList.get(4));
        myLinkedList.addAtHead(4);
        myLinkedList.addAtIndex(5,0);
        myLinkedList.addAtHead(6);
    }
}
