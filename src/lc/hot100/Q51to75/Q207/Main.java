package lc.hot100.Q51to75.Q207;

import java.util.ArrayList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
 *
 * 在选修某些课程之前需要一些先修课程。
 * 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
 *
 * 例如，先修课程对[0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0]]
 * 输出：true
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。
 *
 *
 * 示例 2：
 *
 * 输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
 * 输出：false
 * 解释：总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。
 *
 *
 * 提示：
 *
 * 1 <= numCourses <= 10^5
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * prerequisites[i] 中的所有课程对 互不相同
 *
 * */

class Solution {
    private class Node{
        int course;
        List<Node> next;
        int visit; // 0: 未访问，1: 正在访问，-1: 已访问
        public Node(int num){course = num; next = new ArrayList<>();visit = 0;}
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if(prerequisites.length == 0) return true;

        Node[] courses = new Node[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Node(i);
        }

        for (int[] prerequisite : prerequisites) {
            courses[prerequisite[1]].next.add(courses[prerequisite[0]]);
        }

        for (Node course : courses) {
            if (course.visit == 0){
                if (!dfs(course)){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean dfs(Node node){
        if (node.visit == 1){
            return false;
        }

        if (node.visit == -1){
            return true;
        }

        node.visit = 1;

        for (Node nextCourse : node.next) {
            if (!dfs(nextCourse)){
                return false;
            }
        }

        node.visit = -1;

        return true;
    }
}
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 2;
        int[][] pre = {{1,0},{0,1}};
        System.out.println(solution.canFinish(n,pre));
    }
}
