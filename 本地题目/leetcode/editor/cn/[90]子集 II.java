//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 位运算 数组 回溯 👍 969 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();


    // main
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);// 先排序，让相同的元素靠在⼀起
        backtrack(nums, 0);
        return res;
    }


    // backtrack algrithm
    void backtrack(int[] nums, int start) {
        //
        res.add(new LinkedList<>(track));

        for (int i = start; i < nums.length; i++) {
            // cut : 剪枝逻辑，值相同的相邻树枝，只遍历第⼀条
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // choose
            track.addLast(nums[i]);
            // backtrack
            backtrack(nums, i + 1);
            // revoke
            track.removeLast();
        }

        return;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
