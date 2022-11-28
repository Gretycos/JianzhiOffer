package lc.labuladong.DataStructure.tree.Q315;

import java.util.LinkedList;
import java.util.List;


class Solution {
    private class Pair{
        int val, id;
        Pair(int val, int id){
            this.val = val;
            this.id = id;
        }
    }

    private Pair[] temp;
    private int[] counts;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        counts = new int[n];
        temp = new Pair[n];

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i],i);
        }
        sort(arr, 0, n-1);
        List<Integer> res = new LinkedList<>();
        for (int count : counts) {
            res.add(count);
        }
        return res;
    }

    private void sort(Pair[] arr, int low, int high){
        if (low == high) return;
        int mid = low + (high - low) / 2;
        sort(arr, low, mid);
        sort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private void merge(Pair[] arr, int low, int mid, int high){
        for (int i = low; i <= high; i++){
            temp[i] = arr[i];
        }
        int i = low, j = mid + 1;
        for (int p = low; p <= high; p++){
            if (i == mid + 1){
                // 左半边已完成排序
                arr[p] = temp[j++];
            }else if (j == high + 1){
                arr[p] = temp[i++];
                // 右半边已经完成排序
                counts[arr[p].id] += j - (mid + 1);
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            }else{
                arr[p] = temp[i++];
                counts[arr[p].id] += j - (mid + 1);
            }
        }
    }
}

public class Main {
}
