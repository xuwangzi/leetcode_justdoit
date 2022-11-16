//给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。 
//
// 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
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
// nums 中的所有元素 互不相同 
// 
//
// Related Topics 位运算 数组 回溯 👍 1858 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();


    // main
    public List<List<Integer>> subsets(int[] nums) {
        // 回溯算法
        backtrack(nums, 0);

        return res;
    }


    // 回溯算法核⼼函数，遍历⼦集问题的回溯树
    void backtrack(int[] nums, int start) {
        // 前序位置，每个节点的值都是⼀个⼦集
        res.add(new LinkedList<>(track));

        // 回溯算法标准框架
        for (int i = start; i < nums.length; i++) {
            // 做选择
            track.addLast(nums[i]);

            // 通过 start 参数控制树枝的遍历，避免产⽣重复的⼦集
            backtrack(nums,i+1);

            // 撤销选择
            track.removeLast();

        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
