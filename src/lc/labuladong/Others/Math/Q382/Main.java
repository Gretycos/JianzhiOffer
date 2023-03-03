package lc.labuladong.Others.Math.Q382;

import lc.ListNode;

import java.util.Random;

class Solution {

    private ListNode head;
    private Random rand;
    public Solution(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    public int getRandom() {
        ListNode p = head;
        int i = 0, res = Integer.MAX_VALUE;
        while (p != null){
            i++;
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (rand.nextInt(i) == 0){
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }
}

public class Main {
}
