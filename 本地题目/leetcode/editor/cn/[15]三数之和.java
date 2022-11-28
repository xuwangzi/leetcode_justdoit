//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 5420 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // main
    public List<List<Integer>> threeSum(int[] nums) {
        // sort nums
        Arrays.sort(nums);

        // call nSumTarget
        return nSumTarget(nums, 3, 0, 0);
    }


    // nSumTarget() : nums[] has been sorted
    List<List<Integer>> nSumTarget(int[] nums, int n, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();

        // special case
        if (n < 2 || nums.length < n) return res;

        // base case : 2Sum
        if (n == 2) {
            // two ptrs
            int lo = start, hi = nums.length - 1;

            while (lo < hi) {
                int sum = nums[lo] + nums[hi];
                int left = nums[lo], right = nums[hi];

                if (sum < target) {// lo ->
                    while (lo < hi && nums[lo] == left) lo++;// skip the repeat elements
                } else if (sum > target) {// <- hi
                    while (lo < hi && nums[hi] == right) hi--;// skip the repeat elements
                } else {// target : lo ->  <- hi
                    res.add(Arrays.asList(left, right));
                    while (lo < hi && nums[lo] == left) lo++;// skip the repeat elements
                    while (lo < hi && nums[hi] == right) hi--;// skip the repeat elements
                }
            }

        }

        // recursion
        if (n > 2) {
            for (int i = start; i < nums.length; i++) {
                // get (n-1)Sum
                List<List<Integer>> subs = nSumTarget(nums, n - 1, i + 1, target - nums[i]);

                // (n-1)Sum + nums[i] => nSum
                for (List<Integer> sub : subs) {
                    List<Integer> tmp = new ArrayList<>(sub);
                    tmp.add(nums[i]);

                    res.add(tmp);
                }

                // skip the repeat elements
                while (i < nums.length - 1 && nums[i] == nums[i + 1]) i++;

            }

        }

        return res;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
