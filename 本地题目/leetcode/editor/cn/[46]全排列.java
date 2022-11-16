//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2305 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private List<List<Integer>> res = new LinkedList<>();

    /* 主函数，输⼊⼀组不重复的数字，返回它们的全排列 */
    public List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();

        // 「路径」中的元素会被标记为 true，避免重复使⽤
        boolean[] used = new boolean[nums.length];

        // 回溯算法
        backtrack(nums,track,used);

        return res;
    }


    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素（used[i] 为 false）
    // 结束条件：nums 中的元素全都在 track 中出现
    private void backtrack(int[] nums,LinkedList<Integer> track,boolean[] used){
        // 触发结束条件
        if (track.size()==nums.length){
            res.add(new LinkedList(track));
            return;
        }

        for (int i = 0 ;i<nums.length;i++){
            // 排除不合法的选择
            if (used[i]){
                // nums[i] 已经在 track 中，跳过
                continue;
            }

            // 做选择
            track.add(nums[i]);
            used[i] = true;
            // 进⼊下⼀层决策树
            backtrack(nums, track, used);
            // 取消选择
            track.removeLast();
            used[i]=false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
