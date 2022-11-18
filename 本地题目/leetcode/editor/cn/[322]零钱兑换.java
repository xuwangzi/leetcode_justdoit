//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 👍 2200 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    /* method-01 : 带备忘录memo的递归 */
//    // memorandum
//    /// memo[amount] = coinChange(coins, amount)
//    int[] memo;
//
//
//    // main
//    public int coinChange(int[] coins, int amount) {
//        // memo
//        memo=new int[amount+1];
//        Arrays.fill(memo,-666);
//
//        // dp --- dynamic programming
//        return dp(coins, amount);
//    }
//
//
//    // dynamic programming
//    /// 定义：要凑出⾦额 n，⾄少要 dp(coins, n) 个硬币
//    int dp(int[] coins, int amount) {
//        // base case
//        if (amount == 0) return 0;
//        if (amount < 0) return -1;
//
//        // check memo
//        if (memo[amount] != -666) return memo[amount];
//
//        int res = Integer.MAX_VALUE;
//        for (int coin : coins) {
//            // subProblem
//            int subProblem = dp(coins, amount - coin);
//            /// subProblem false
//            if (subProblem == -1) continue;
//
//            // optimal
//            res = Math.min(res, subProblem + 1);
//        }
//
//        // take memo
//        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
//
//        return memo[amount];
//
//    }


    /* method-02 : dp数组的迭代解法 */
    int coinChange(int[] coins, int amount) {
        // dp table
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        // base case
        dp[0] = 0;
        // outter loop
        for (int i = 0; i < dp.length; i++) {
            // inner loop
            for (int coin : coins) {
                // subproblem false
                if (i - coin < 0) continue;

                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }

        return dp[amount] == (amount + 1) ? -1 : dp[amount];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
