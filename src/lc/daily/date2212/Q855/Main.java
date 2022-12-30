package lc.daily.date2212.Q855;

import java.util.PriorityQueue;
import java.util.TreeSet;

class ExamRoom {
    private final int n;
    private final PriorityQueue<int[]> pq;
    private final TreeSet<Integer> seats;

    public ExamRoom(int n) {
        this.n = n;
        pq = new PriorityQueue<>((r1,r2)->{
            int d1 = (r1[1] - r1[0]) / 2, d2 = (r2[1] - r2[0]) / 2;
            return d1 != d2? d2 - d1 : r1[0] - r2[0];
        });
        seats = new TreeSet<>();
    }

    public int seat() {
        if (seats.size() == 0){
            seats.add(0);
            return 0;
        }
        // 最左座位跟最左学生的距离为leftLen = seats.first() - 0
        // 最右座位跟最右学生的距离为rightLen = n-1 - seats.last()
        int leftLen = seats.first(), rightLen = n - 1 - seats.last();
        while (seats.size() >= 2){
            int[] cur = pq.peek();
            if (seats.contains(cur[0])
                    && seats.contains(cur[1])
                    && seats.higher(cur[0]) == cur[1]){
                int curDist = (cur[1] - cur[0]) / 2;
                // 最左或最右的位置更优
                if (curDist < rightLen || curDist <= leftLen){
                    break;
                }
                pq.poll();
                int mid = cur[0] + curDist;
                pq.offer(new int[]{cur[0], mid});
                pq.offer(new int[]{mid, cur[1]});
                seats.add(mid);
                return mid;
            }else{
                pq.poll();
            }
        }
        if (rightLen > leftLen){
            pq.offer(new int[]{seats.last(), n-1});
            seats.add(n-1);
            return n-1;
        }else{
            pq.offer(new int[]{0, seats.first()});
            seats.add(0);
            return 0;
        }
    }

    public void leave(int p) {
        if (p != seats.first() && p != seats.last()){
            int pre = seats.lower(p), next = seats.higher(p);
            pq.offer(new int[]{pre,next});
        }
        seats.remove(p);
    }
}


public class Main {
}
