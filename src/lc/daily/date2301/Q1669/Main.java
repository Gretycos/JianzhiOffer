package lc.daily.date2301.Q1669;

import lc.ListNode;

class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode l2End = list2;
        while (l2End.next != null) l2End = l2End.next;
        ListNode aLast = list1;
        int i;
        for (i = 0; i + 1 < a; i++) {
            aLast = aLast.next;
        }
        ListNode bNext = aLast;
        for (;i <= b; i++){
            bNext = bNext.next;
        }
        aLast.next = list2;
        l2End.next = bNext;
        return list1;
    }
}

public class Main {
}
