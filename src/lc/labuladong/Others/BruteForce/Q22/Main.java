package lc.labuladong.Others.BruteForce.Q22;

import java.util.LinkedList;
import java.util.List;

class Solution {
    private List<String> res;
    private StringBuilder track;
    public List<String> generateParenthesis(int n) {
        res = new LinkedList<>();
        track = new StringBuilder();
        backtrack(n,n);
        return res;
    }

    private void backtrack(int left, int right){
        // 若左括号剩下的多，说明不合法
        if (left > right) return;
        // 数量小于 0 肯定是不合法的
        if (left < 0 || right < 0) return;
        // 当所有括号都恰好用完时，得到一个合法的括号组合
        if (left == 0 && right == 0){
            res.add(track.toString());
            return;
        }

        // 尝试放一个左括号
        track.append('(');
        backtrack(left-1, right);
        track.deleteCharAt(track.length()-1);

        // 尝试放一个右括号
        track.append(')');
        backtrack(left, right-1);
        track.deleteCharAt(track.length()-1);
    }
}

public class Main {
}
