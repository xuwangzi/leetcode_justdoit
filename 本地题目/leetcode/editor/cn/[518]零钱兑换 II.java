//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
//
// Related Topics 数组 动态规划 👍 955 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int change(int amount, int[] coins) {
        // define : dp[i] 表示 amount=i 对应的组合数
        // init : dp={0,0,...}
        int[] dp=new int[amount + 1];

        // base case
        dp[0] = 1;

        // state transition
        // 注意：嵌套顺序 是 先计算只有第一种硬币的情况、再增加第二种硬币、依此类推；
        //      原因 是 硬币的不同排列记录为同一组合
        for (int coin : coins) {
            for (int i = 1; i <= amount; i++) {
                if (i - coin >= 0)
                    dp[i] += dp[i - coin];
            }
        }

        return dp[amount];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
