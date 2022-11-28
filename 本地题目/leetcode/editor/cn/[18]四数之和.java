//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 双指针 排序 👍 1439 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // main
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // sort nums
        Arrays.sort(nums);

        // call nSumTarget
        return nSumTarget(nums, 4, 0, (long)target);// target 和 nums[i]+nums[j] 可能超过 Integer.MAX_VALUE
    }


    // nSumTarget() : nums[] has been sorted
    List<List<Integer>> nSumTarget(int[] nums, int n, int start, long target) {
        List<List<Integer>> res = new ArrayList<>();

        // special case
        if (n < 2 || nums.length < n) return res;

        // base case : 2Sum
        if (n == 2) {
            // two ptrs
            int lo = start, hi = nums.length - 1;

            while (lo < hi) {
                long sum = nums[lo] + nums[hi];
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
                List<List<Integer>> subs = nSumTarget(nums, n - 1, i + 1, target - (long)nums[i]);

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
