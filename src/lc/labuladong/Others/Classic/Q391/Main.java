package lc.labuladong.Others.Classic.Q391;

import java.util.*;

class Point {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    // 发生哈希冲突了会调用equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Objects.equals(x, point.x) && Objects.equals(y, point.y);
    }
}

class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        // 完美矩形的左下和右上
        int X1 = Integer.MAX_VALUE, Y1 = Integer.MAX_VALUE, // 左下
                X2 = Integer.MIN_VALUE, Y2 = Integer.MIN_VALUE; // 右上


        int actualArea = 0;
        Set<Point> set = new HashSet<>();
        for (int[] rectangle : rectangles) {
            // 小矩形的左下和右上
            int x1 = rectangle[0], y1 = rectangle[1], // 左下
                    x2 = rectangle[2], y2 = rectangle[3]; // 右上

            X1 = Math.min(X1, x1);
            Y1 = Math.min(Y1, y1);
            X2 = Math.max(X2, x2);
            Y2 = Math.max(Y2, y2);
            actualArea += (x2 - x1) * (y2 - y1);

            // 记录最终形成的图形中的顶点
            Point p1 = new Point(x1, y1), p2 = new Point(x1, y2),
                    p3 = new Point(x2, y1), p4 = new Point(x2, y2);
            List<Point> points = Arrays.asList(p1, p2, p3, p4);
            for (Point p : points) {
                if (set.contains(p)) {
                    set.remove(p);
                } else {
                    set.add(p);
                }
            }
        }
        // 判断面积是否相同
        int expectedArea = (X2 - X1) * (Y2 - Y1);
        if (actualArea != expectedArea) return false;

        // 判断最终留下的顶点个数是否为 4
        if (set.size() != 4) return false;

        // 判断留下的 4 个顶点是否是完美矩形的顶点
        if (!set.contains(new Point(X1, Y1))) return false;
        if (!set.contains(new Point(X1, Y2))) return false;
        if (!set.contains(new Point(X2, Y1))) return false;
        if (!set.contains(new Point(X2, Y2))) return false;

        return true;
    }
}

public class Main {
}
