package lc.hot100.Q76toQ100.Q309;

/**
 * 给定一个整数数组prices，其中第prices[i]表示第i天的股票价格 。
 *
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 *
 * 示例 1:
 *
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 * 示例 2:
 *
 * 输入: prices = [1]
 * 输出: 0
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 * */

class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 1) return 0;
        // 定义buy[i]/sell[i]/cool[i]为第i天最后一个操作是(buy/sell/cool)的最大收益
        int[] buy = new int[n], // 持有，可能是今日买入，可能是之前买入的一直没动
              sell = new int[n], // 不持有，今天卖出
              cool = new int[n]; // 不持有，可能是之前卖出，可能是一直没动
        // 第一天只能买入
        // sell[0] = 0，当天卖日卖出
        // cool[0] = 0，第一天无操作
        buy[0] = - prices[0];
        for (int i = 1; i < n; i++) {
            buy[i] = Math.max(buy[i-1],cool[i-1] - prices[i]);
            sell[i] = buy[i-1] + prices[i];
            cool[i] = Math.max(sell[i-1],cool[i-1]);
        }
        return Math.max(sell[n-1],cool[n-1]);
    }
}


public class Main {
}
