//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。 
//
// 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子
//序列。 
//
// 示例 1： 
//
// 
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,3,2,3]
//输出：4
// 
//
// 示例 3： 
//
// 
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2500 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶： 
//
// 
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗? 
// 
//
// Related Topics 数组 二分查找 动态规划 👍 2880 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* method-01 : dp  O(N^2) */
    public int lengthOfLIS(int[] nums) {
        // define: dp[i] 表示以 nums[i] 这个数结尾的最⻓递增⼦序列的⻓度
        int[] dp = new int[nums.length];

        // base case
        Arrays.fill(dp, 1);

        // dp
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        // getRes
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;

    }


//    /* method-02 : ⼆分查找（patience game） O(NlogN)*/
//    public int lengthOfLIS(int[] nums) {
//        // 牌堆顶
//        int[] top = new int[nums.length];
//        // 牌堆数 初始化为 0
//        int piles = 0;
//
//        // patience sorting（耐⼼排序）
//        for (int i = 0; i < nums.length; i++) {
//            // 要处理的扑克牌
//            int poker = nums[i];
//
//            /***** 搜索左侧边界的⼆分查找 *****/
//            // [left, right)
//            int left=0,right=piles;
//            while (left<right){
//                int mid=left+(right-left)/2;
//
//                if (top[mid]>poker) {
//                    right=mid;
//                }else if (top[mid]<poker){
//                    left=mid+1;
//                }else {
//                    right=mid;
//                }
//            }
//            /******************************/
//
//            // 没找到合适的牌堆，新建⼀堆
//            if (left==piles) piles++;
//            // 把这张牌放到牌堆顶
//            top[left]=poker;
//        }
//
//        // 牌堆数就是 LIS ⻓度
//        // 最⻓递增⼦序列 Longest Increasing Subsequence
//        return piles;
//
//    }

}
//leetcode submit region end(Prohibit modification and deletion)
