//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// Related Topics 数组 回溯 👍 1166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res  = new LinkedList<>();
    // 记录回溯的路径
    LinkedList<Integer> track = new LinkedList<>();
    // 记录 track 中的元素之和
    int trackSum = 0;


    // main
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // special case
        if (candidates.length==0) return res;

        // 先排序，让相同的元素靠在⼀起
        Arrays.sort(candidates);

        // backtrack
        backtrack(candidates,0,target);

        return res;
    }


    // backtrack
    void backtrack(int[] candidates,int start,int target){
        // base case
        if (trackSum==target){
            res.add(new LinkedList<>(track));
            return;
        } else if (trackSum>target) {
            return;
        }

        // backtrack frame
        for (int i = start; i < candidates.length; i++) {
            // cut : 剪枝逻辑，值相同的树枝，只遍历第⼀条
            if (i>start && candidates[i]==candidates[i-1]){
                continue;
            }

            // choose
            track.add(candidates[i]);
            trackSum+=candidates[i];

            // backtrack
            backtrack(candidates,i+1,target);

            // revoke
            track.removeLast();
            trackSum -= candidates[i];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
