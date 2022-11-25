//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2： 
//
// 
//输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 400 
// 
//
// Related Topics 数组 动态规划 👍 2356 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // memo
    int[] memo;


    // main
    public int rob(int[] nums) {
        // init memo
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        // dp
        return dp(nums, 0);

    }


    // dp
    int dp(int[] nums, int start) {
        // base case
        if (start >= nums.length) return 0;

        // check memo
        if (memo[start] != -1) return memo[start];

        // state transition & take memo
        memo[start] = Math.max(
                dp(nums, start + 1),
                dp(nums, start + 2) + nums[start]
        );


        return memo[start];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
