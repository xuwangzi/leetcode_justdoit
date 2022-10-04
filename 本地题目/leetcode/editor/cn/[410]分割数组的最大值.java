//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。 
//
// 设计一个算法使得这 m 个子数组各自和的最大值最小。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [7,2,5,10,8], m = 2
//输出：18
//解释：
//一共有四种方法将 nums 分割为 2 个子数组。 
//其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
//因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4,5], m = 2
//输出：9
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,4,4], m = 3
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// 0 <= nums[i] <= 10⁶ 
// 1 <= m <= min(50, nums.length) 
// 
//
// Related Topics 贪心 数组 二分查找 动态规划 👍 744 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // binarySearch
    public int splitArray(int[] nums, int m) {
        // 子数组之和 [max(nums), sum(nums)]
        int left = 1, right = 0;
        for (int num :
                nums) {
            left = Math.max(left, num);
            right += num;
        }

        // special case
        // nums = {0,0,...,0}
        if (right == 0) {
            return 0;
        }

        // loop
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (f(nums, mid) < m) {
                right = mid - 1;
            } else if (f(nums, mid) > m) {
                left = mid + 1;
            } else {// f(nums, mid) == m
                right = mid - 1;
            }

        }

        return left;


    }

    // 当子数组和 <= x 时，共有 f(x) 个子数组
    int f(int[] nums, int x) {
        int res = 0;
        int sum = 0;

        for (int num :
                nums) {
            assert num <= x;

            sum += num;

            if (sum > x) {
                res++;
                sum = num;
            }

        }

        if (sum > 0) {
            res++;
        }

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
