package lc.dp.Q22;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Tsong
 * @Date 2023/9/14 15:41
 */
class Solution {
    private List<String> res;
    private StringBuilder stringBuilder;
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        stringBuilder = new StringBuilder();
        backtrack(n, n);
        return res;
    }

    private void backtrack(int left, int right) {
        if (left > right) return;
        if (left < 0 || right < 0) return;
        if (left == 0 && right == 0) {
            res.add(stringBuilder.toString());
            return;
        }

        stringBuilder.append('(');
        backtrack(left - 1, right);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        stringBuilder.append(')');
        backtrack(left, right - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.generateParenthesis(3));
    }
}
