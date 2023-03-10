package lc.labuladong.Others.Classic.Q855;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class ExamRoom {
    private Map<Integer, int[]> startMap, endMap; // 存放[p,x] 或者 [x,p]
    private TreeSet<int[]> pq; // 根据线段长度从小到大存放所有线段
    private int N;

    public ExamRoom(int n) {
        N = n;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>((a, b) -> {
            int distA = dist(a), distB = dist(b);
            // 距离升序，起点降序
            // 因为下面要取最后一个元素计算
            return distA == distB ? b[0] - a[0] : distA - distB;
        });
        // 在有序集合中先放一个虚拟线段
        addInterval(new int[]{-1, N});
    }

    public int seat() {
        // 从有序集合拿出最长的线段
        int[] longest = pq.last();
        int x = longest[0], y = longest[1];
        int seat;
        if (x == -1) { // 遇到虚拟边界
            seat = 0;
        } else if (y == N) { // 遇到虚拟边界
            seat = N - 1;
        } else { // 坐中点
            seat = (y - x) / 2 + x;
        }

        // 将最长的线段分成两段
        int[] left = new int[]{x, seat};
        int[] right = new int[]{seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        // 将 p 左右的线段找出来
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);
        // 合并两个线段成为一个线段
        int[] merged = new int[]{left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    /* 去除一个线段 */
    private void removeInterval(int[] intv) {
        pq.remove(intv);
        startMap.remove(intv[0]);
        endMap.remove(intv[1]);
    }

    /* 增加一个线段 */
    private void addInterval(int[] intv) {
        pq.add(intv);
        startMap.put(intv[0], intv);
        endMap.put(intv[1], intv);
    }

    /* 计算一个线段的长度 */
    private int dist(int[] intv) {
        int x = intv[0], y = intv[1];
        if (x == -1) return y;
        if (y == N) return N - 1 - x;
        // 不能简单地让它计算一个线段两个端点间的长度，而是让它计算该线段中点和端点之间的长度。
        return (y - x) / 2;
    }
}

public class Main {
}
