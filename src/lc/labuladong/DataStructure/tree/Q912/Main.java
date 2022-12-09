package lc.labuladong.DataStructure.tree.Q912;

import java.util.Random;

// 归并排序
class Merge{
    private static int[] temp;
    public static void sort(int[] nums){
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private static void sort(int[] nums, int low, int high){
        if (low == high){
            return;
        }

        int mid = low + (high - low) / 2;
        sort(nums, low, mid);
        sort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high){
        // 先复制到temp，方便后续赋值
        for (int i = low; i <= high; i++) {
            temp[i] = nums[i];
        }
        int i = low, j = mid + 1;
        for (int p = low; p<= high; p++){
            if (i == mid + 1){
                // 左半边已经合并完成
                nums[p] = temp[j++];
            }else if (j == high + 1){
                nums[p] = temp[i++];
            }else if (temp[i] > temp[j]){
                // 升序排序
                nums[p] = temp[j++];
            }else{
                nums[p] = temp[i++];
            }
        }
    }
}

// 快速排序
class Quick{
    public static void sort(int[] nums){
        shuffle(nums); // 不随机化的话可能会退化到O(n^2)
        sort(nums, 0, nums.length-1);
    }

    private static void sort(int[] nums, int lo, int hi){
        if (lo >= hi) return;
        // 划分nums[lo...hi]
        // 使得nums[lo...p-1] <= nums[p] <= nums[p+1...hi]
        // 返回划分点p
        int p = partition(nums, lo, hi);
        sort(nums, lo, p - 1);
        sort(nums, p + 1, hi);
    }

    private static int partition(int[] nums, int lo, int hi){
        int pivot = nums[lo];
        // 定义开区间边界i,j
        // [lo, i) <= pivot, (j, hi] > pivot
        int i = lo + 1, j = hi;
        // 当i > j时，循环结束，保证[lo,hi]都被覆盖
        // 等号的目的：
        // 解决array的长度只有2的edge case，其中一个被抓去做了pivot，另一个必须得验证一下是不是比pivot大，不是的话还是要swap一下。
        // 如果长度只有1，那么进不了这个while循环
        while (i <= j){
            while (i < hi && nums[i] <= pivot){
                i++;
            }
            while (j > lo && nums[j] >= pivot){
                j--;
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, lo, j);
        return j;
    }

    // 洗牌算法
    private static void shuffle(int[] nums){
        Random random = new Random();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int r = i + random.nextInt(n - i);
            swap(nums, i, r);
        }
    }

    private static void swap(int[] nums, int i, int j){
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

}
class Solution {
    public int[] sortArray(int[] nums) {
        long startTime, endTime;
        startTime = System.currentTimeMillis();
        Merge.sort(nums);
        endTime = System.currentTimeMillis();
        System.out.println("MergeSort: " + (endTime - startTime) + "ms");
        startTime = System.currentTimeMillis();
        Quick.sort(nums);
        endTime = System.currentTimeMillis();
        System.out.println("QuickSort: " + (endTime - startTime) + "ms");
        return nums;
    }
}

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        int n = (int) 1e4 * 5;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(n);
        }
        Solution solution = new Solution();
        solution.sortArray(nums);
    }
}
