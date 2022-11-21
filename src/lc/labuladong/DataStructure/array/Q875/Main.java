package lc.labuladong.DataStructure.array.Q875;

class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // 速度区间
        int left = 1, right = 0;
        for (int pile : piles) {
            if (pile > right) right = pile;
        }
        while(left <= right){
            int mid = left + (right - left) / 2;
            long needH = getH(piles, mid);
            if (needH == h){
                right = mid - 1;
            }else if (needH < h){
                right = mid - 1;
            }else if (needH > h){
                left = mid + 1;
            }
        }
        return left;
    }

    private long getH(int[] piles, int k){
        long h = 0;
        for (int pile : piles) {
            h += (pile + k - 1) / k;
        }
        return h;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] piles = {805306368,805306368,805306368};
        solution.minEatingSpeed(piles,1000000000);
    }
}
