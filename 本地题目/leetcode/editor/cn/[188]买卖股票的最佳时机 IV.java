//给定一个整数数组 prices ，它的第 i 个元素 prices[i] 是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 2, prices = [2,4,1]
//输出：2
//解释：在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。 
//
// 示例 2： 
//
// 
//输入：k = 2, prices = [3,2,6,5,0,3]
//输出：7
//解释：在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
//     随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 
//。 
//
// 
//
// 提示： 
//
// 
// 0 <= k <= 100 
// 0 <= prices.length <= 1000 
// 0 <= prices[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 832 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 原始版本
    public int maxProfit(int input_k, int[] prices) {
        int max_k = input_k, n = prices.length;

        if (max_k > n / 2) {
            int sum = 0;

            for (int i = 0; i < prices.length - 1; i++) {
                if (prices[i] < prices[i + 1]) {
                    sum += (prices[i + 1] - prices[i]);
                }
            }

            return sum;

        }

        // dp[day][trade_times][rest 0 or 1]
        int[][][] dp = new int[n][max_k + 1][2];

        for (int i = 0; i < n; i++) {
            for (int k = 1; k <= max_k; k++) {
                if (i == 0) {
                    // base case
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                } else {
                    // state transition
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }

        return dp[n - 1][max_k][0];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
