//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
//
// Related Topics 数组 分治 动态规划 👍 5463 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    /* method-01 : 动态规划 */
//    public int maxSubArray(int[] nums) {
//        int n = nums.length;
//
//        // special case
//        if (n == 0) return 0;
//
//        // define : dp[i] 记录以 nums[i] 为结尾的「最⼤⼦数组和」
//        int[] dp = new int[n];
//
//        // base case
//        dp[0] = nums[0];
//
//        // state transition
//        for (int i = 1; i < n; i++) {
//            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
//        }
//
//        // getRes
//        int res = Integer.MIN_VALUE;
//        for (int i = 0; i < n; i++) {
//            res = Math.max(res,dp[i]);
//        }
//        return res;
//
//    }


//    /* method-01-优化 : 动态规划 */
//    public int maxSubArray(int[] nums) {
//        int n = nums.length;
//
//        // special case
//        if (n == 0) return 0;
//
//        // define : dp[i] 记录以 nums[i] 为结尾的「最⼤⼦数组和」
//        // base case
//        int dp_0 = nums[0], dp_1 = 0, res = dp_0;
//
//        // state transition
//        for (int i = 1; i < n; i++) {
//            // dp[i] = max(nums[i], nums[i] + dp[i-1])
//            dp_1 = Math.max(nums[i], dp_0 + nums[i]);
//            dp_0=dp_1;
//
//            // getRes
//            res=Math.max(res,dp_1);
//        }
//
//        return res;
//
//    }


    /* method-02 : 前缀和 */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        preSum[0] = 0;
        // 构造 nums 的前缀和数组
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        int res = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 维护 minVal 是 preSum[0..i] 的最⼩值
            minVal=Math.min(minVal,preSum[i]);
            // 以 nums[i] 结尾的最⼤⼦数组和就是 preSum[i+1] - min(preSum[0..i])
            res=Math.max(res,preSum[i+1]-minVal);
        }

        return res;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
