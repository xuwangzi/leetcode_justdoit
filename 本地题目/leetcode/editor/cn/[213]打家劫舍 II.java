//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [1,2,3]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
//
// Related Topics 数组 动态规划 👍 1203 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // memo
    int[][] memo;


    // main
    public int rob(int[] nums) {
        // special
        if (nums==null) return 0;
        if (nums.length==1) return nums[0];

        // init memo
        memo = new int[nums.length][2];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // dp
        return Math.max(dp(nums, 0, nums.length - 2), dp(nums, 1, nums.length - 1));

    }


    // dp
    int dp(int[] nums, int start, int end) {
        // base case
        if (start > end) return 0;

        // check memo
        int index_end = (end == (nums.length - 2)) ? 0 : 1;
        if (memo[start][index_end] != -1) return memo[start][index_end];

        // state transition & take memo
        memo[start][index_end] = Math.max(
                dp(nums, start + 1, end),
                dp(nums, start + 2, end) + nums[start]
        );


        return memo[start][index_end];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
