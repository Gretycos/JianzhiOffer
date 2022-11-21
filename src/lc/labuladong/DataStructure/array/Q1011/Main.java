package lc.labuladong.DataStructure.array.Q1011;


class Solution {
    public int shipWithinDays(int[] weights, int days) {
        // 搜索区间
        // 最小是货物的最大值，最大是货物的总重量
        int left = 0, right = 0;
        for (int weight : weights) {
            if (weight > left) left = weight;
            right += weight;
        }
        while (left <= right){
            int mid = left + (right - left) / 2;
            int needDays = getDays(weights, mid);
            if (needDays == days){
                right = mid - 1;
            }else if (needDays > days){ // 需要天数太多，需要增加货运量
                left = mid + 1;
            }else if (needDays < days){ // 需要天数不足，需要减少货运量
                right = mid - 1;
            }
        }
        return left;

    }

    private int getDays(int[] weights, int load){
        int days = 0, idx = 0;
        while (idx < weights.length){
            days++;
            int sum = 0;
            while (idx < weights.length){
                sum += weights[idx++];
                if (sum > load){
                    idx --;
                    break;
                }
            }
        }
        return days;
    }
}


public class Main {
}
