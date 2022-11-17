//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1238 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;


    // main
    public List<List<Integer>> permuteUnique(int[] nums) {
        used = new boolean[nums.length];

        // 先排序，让相同的元素靠在⼀起
        Arrays.sort(nums);

        // backtrack
        backtrack(nums);

        return res;
    }


    // backtrack
    void backtrack(int[] nums) {
        // base case
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        // frame
        for (int i = 0; i < nums.length; i++) {
            // cut
            if (used[i]) continue;
            /// 新添加的剪枝逻辑，固定相同的元素在排列中的相对位置
            /// 如果前⾯的相邻相等元素没有⽤过，则跳过
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;

            // choose
            track.add(nums[i]);
            used[i] = true;
            // backtrack
            backtrack(nums);
            // revoke
            track.removeLast();
            used[i] = false;

        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
