//给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,5,11,5]
//输出：true
//解释：数组可以分割成 [1, 5, 5] 和 [11] 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,5]
//输出：false
//解释：数组不能分割成两个元素和相等的子集。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 100 
// 
//
// Related Topics 数组 动态规划 👍 1564 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // main
    public boolean canPartition(int[] nums) {
        // sum mod 2
        int sum = 0;
        for (int i = 0; i < nums.length; i++)
            sum += nums[i];

        if (sum % 2 != 0) return false;

        // target
        int target = sum / 2;

        // define : dp[i][j] 表示 nums的前i个元素 是否可以得到 target=j
        boolean[][] dp = new boolean[nums.length+1][target + 1];

        // base case
        for (int i = 0; i < nums.length; i++) {
            dp[i][0] = true;
        }

        // state transition
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= target; j++) {
                if (j - nums[i-1] < 0) {
                    // 背包容量不⾜，不能装⼊第 i 个物品
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 装⼊或不装⼊背包
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[nums.length][target];

    }
}
//leetcode submit region end(Prohibit modification and deletion)
