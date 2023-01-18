package lc.daily.date2301.Q1825;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

class MKAverage {
    private final int m, k;
    private long sum;
    // 维护数据流
    private final Deque<Integer> q;
    // 存储最小k个数，剩余数，最大k个数，和它们的个数
    private final TreeMap<Integer,Integer> lo, mid, hi;
    private int sizeLo, sizeHi; // 统计lo和hi中存储的数的数量


    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        q = new ArrayDeque<>();
        lo = new TreeMap<>();
        mid = new TreeMap<>();
        hi = new TreeMap<>();
    }

    public void addElement(int num) {
        // 先考虑插入3个有序数组
        if (lo.isEmpty() || num <= lo.lastKey()){ // lo为空 || num <= max(lo)
            // 相当于lo[num] ++
            lo.merge(num, 1, Integer::sum);
            sizeLo ++;
        } else if (hi.isEmpty() || num >= hi.firstKey()){ // hi 为空 || num >= min(hi)
            // hi[num] ++
            hi.merge(num, 1, Integer::sum);
            sizeHi ++;
        } else { // max(lo) < num < min(hi)
            // mid[num] ++;
            mid.merge(num, 1, Integer::sum);
            sum += num;
        }
        // 插入数据流队列
        q.addLast(num);
        // 如果q的size > m，需要移除队头
        if (q.size() > m){
            int x = q.removeFirst();
            // 查看x属于哪个有序集合
            if (lo.containsKey(x)){
                // if(--lo[x] == 0)
                if (lo.merge(x, -1, Integer::sum) == 0){
                    lo.remove(x);
                }
                sizeLo --;
            } else if (hi.containsKey(x)){
                // if(--hi[x] == 0)
                if (hi.merge(x, -1, Integer::sum) == 0){
                    hi.remove(x);
                }
                sizeHi --;
            } else {
                // if(--mid[x] == 0)
                if (mid.merge(x, -1, Integer::sum) == 0){
                    mid.remove(x);
                }
                sum -= x;
            }
        }

        // 检查lo hi是否符合只含k个元素
        // 超出k的情况
        while (sizeLo > k){
            // 把超出k的最大的数从最小k个集合中拿出，加入sum
            int x = lo.lastKey();
            if (lo.merge(x, -1, Integer::sum) == 0){
                lo.remove(x);
            }
            sizeLo --;
            mid.merge(x, 1, Integer::sum);
            sum += x;
        }
        while (sizeHi > k){
            int x = hi.firstKey();
            if (hi.merge(x, -1, Integer::sum) == 0){
                hi.remove(x);
            }
            sizeHi --;
            mid.merge(x, 1, Integer::sum);
            sum += x;
        }
        // 不足k的情况
        while (sizeLo < k && !mid.isEmpty()){
            int x = mid.firstKey();
            if (mid.merge(x, -1, Integer::sum) == 0){
                mid.remove(x);
            }
            lo.merge(x, 1, Integer::sum);
            sizeLo ++;
            sum -= x;
        }
        while (sizeHi < k && !mid.isEmpty()){
            int x = mid.lastKey();
            if (mid.merge(x, -1, Integer::sum) == 0){
                mid.remove(x);
            }
            hi.merge(x, 1, Integer::sum);
            sizeHi ++;
            sum -= x;
        }
    }

    public int calculateMKAverage() {
        return q.size() < m ? -1 : (int) (sum / (q.size() - k * 2));
    }
}


public class Main {
}
