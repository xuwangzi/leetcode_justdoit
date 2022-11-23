//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 
//
// 示例 1: 
//
// 
//输入：prices = [3,3,5,0,0,3,1,4]
//输出：6
//解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2： 
//
// 
//输入：prices = [1,2,3,4,5]
//输出：4
//解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3： 
//
// 
//输入：prices = [7,6,4,3,1] 
//输出：0 
//解释：在这个情况下, 没有交易完成, 所以最大利润为 0。 
//
// 示例 4： 
//
// 
//输入：prices = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= prices.length <= 10⁵ 
// 0 <= prices[i] <= 10⁵ 
// 
//
// Related Topics 数组 动态规划 👍 1265 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/*
 「选择」
    买⼊、卖出、⽆操作

 「状态」

    第⼀个是天数，第⼆个是允许交易的最⼤次数，第三个是当前的持有状态（即之前说的 rest 的状态，我们不妨⽤ 1 表示持有，0 表示没有持有）

     dp[i][k][0 or 1]
     0 <= i <= n - 1, 1 <= k <= K
     n 为天数，⼤ K 为交易数的上限，0 和 1 代表是否持有股票。
     此问题共 n × K × 2 种状态，全部穷举就能搞定。


 「状态转移框架」

     base case：
     dp[-1][...][0] = dp[...][0][0] = 0
     dp[-1][...][1] = dp[...][0][1] = -infinity

     状态转移⽅程：
     dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
 */
class Solution {

//    // 原始版本
//    public int maxProfit(int[] prices) {
//        int max_k = 2, n = prices.length;
//
//        // dp[day][trade_times][rest 0 or 1]
//        int[][][] dp = new int[n][max_k + 1][2];
//
//        for (int i = 0; i < n; i++) {
//            for (int k = 1; k <= max_k; k++) {
//                if (i == 0) {
//                    // base case
//                    dp[i][k][0] = 0;
//                    dp[i][k][1] = -prices[i];
//                } else {
//                    // state transition
//                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
//                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
//                }
//            }
//        }
//
//        return dp[n - 1][max_k][0];
//
//    }


    // 空间复杂度优化版本
    public int maxProfit(int[] prices) {
        // base case : i = day = -1
        // define : dp[day][trade_times][rest 0 or 1]
        int dp_i_1_0 = 0, dp_i_1_1 = Integer.MIN_VALUE,
                dp_i_2_0 = 0, dp_i_2_1 = Integer.MIN_VALUE;

        // state transition
        for (int price : prices) {
            dp_i_2_0 = Math.max(dp_i_2_0, dp_i_2_1 + price);
            dp_i_2_1 = Math.max(dp_i_2_1, dp_i_1_0 - price);
            dp_i_1_0 = Math.max(dp_i_1_0, dp_i_1_1 + price);
            dp_i_1_1 = Math.max(dp_i_1_1, 0 - price);
        }


        return dp_i_2_0;

    }

}
//leetcode submit region end(Prohibit modification and deletion)
