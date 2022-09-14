package lc.daily.date2209.Q670;


/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 *
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 *
 * 给定数字的范围是[0, 10^8]
 **/

class Solution {
    public int maximumSwap(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        // 判断是否是降序
        boolean isDesc = true;
        int i; // 发生升序的下标
        for (i = 0; i < n - 1; i++){
            if (s[i] < s[i+1]){
                isDesc = false;
                break;
            }
        }
        // 降序直接返回
        if (isDesc) return num;
        // 查找i后面的最后一个最大值及其下标
        int[] max = findMaxAfter(s,i);
        // 查找比max[0]小的位置并交换
        for (i = 0; i < n; i++){
            if (s[i] < (char) max[0]){
                char temp = s[i];
                s[i] = s[max[1]];
                s[max[1]] = temp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(s));
    }

    /**
     * 查找位置p后面最大的数及其下标*/
    private int[] findMaxAfter(char[] s, int p){
        char max = s[p];
        int i = p;
        for (int j = p+1; j < s.length; j++){
            if (s[j] >= max){
                max = s[j];
                i = j;
            }
        }
        return new int[]{max,i};
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int num = 1993;
        System.out.println(solution.maximumSwap(num));
    }
}
