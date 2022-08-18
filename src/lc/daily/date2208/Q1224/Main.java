package lc.daily.date2208.Q1224;

/**
 * 给你一个正整数数组nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 *
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,2,1,1,5,3,3,5]
 * 输出：7
 * 解释：对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，
 * 就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
 * 示例 2：
 *
 * 输入：nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
 * 输出：13
 *
 *
 * 提示：
 *
 * 2 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 *
 * */


class Solution {
    // 这里的关键点是要分类讨论好每一种情况，我这里归纳有三种情况：
    //
    // 1.所有数的频率都是1，去掉任意一个数都满足条件
    // 2.去掉一个数后其他数的频率相等，如[2,2,1,1,3]去掉一个3
    // 3.去掉一个数后包含当前数的所有数频率相等，如[2,2,1,1,3,3,3]去掉一个3
    //
    //
    public int maxEqualFreq(int[] nums) {
        int[] counts = new int[100001]; // 统计数字的次数
        int[] freq = new int[100001]; // 统计次数的频数
        int res = 0, maxCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i], len = i + 1;
            int count = counts[num]; // 该数字先前出现的次数
            // 如果该数字之前出现过，则其对应的次数的频数--
            // 比如[2,2,2]
            // 当i=0时，counts[2]=1,freq[1]=1
            // 当i=1时，counts[2]=2,freq[1]=0, freq[2]=1
            // 当i=3时，counts[2]=3,freq[1]=0,freq[2]=1,freq[3]=1
            if (count > 0){
                freq[count] --;
            }
            // 该数字当前出现的次数++
            counts[num] = ++count;
            // 该次数的频数++
            freq[count]++;
            // 更新最大出现的次数
            maxCount = Math.max(maxCount,count);

            // 根据3种符合的情况判断最长前缀
            if (maxCount == 1 // 所有的数的出现次数是1，去掉任意一个数满足条件
                    // 去掉一个出现1次的数后，其他数出现的次数相同
                    || maxCount * freq[maxCount] + 1 == len
                    // 去掉1个出现最多的数，剩下的包括这个数，出现次数相同
                    || maxCount + (maxCount - 1) * freq[maxCount-1] == len){
                    res = Math.max(res,len);
            }
        }
        return res;
    }
}


public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1,1,1,2,2,2,3,3,3,4,4,4,5};
        System.out.println(solution.maxEqualFreq(nums));
    }
}
