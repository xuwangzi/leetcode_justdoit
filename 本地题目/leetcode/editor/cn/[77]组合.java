//给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 
//
// 你可以按 任何顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 4, k = 2
//输出：
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
//
// 示例 2： 
//
// 
//输入：n = 1, k = 1
//输出：[[1]] 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 1 <= k <= n 
// 
//
// Related Topics 回溯 👍 1196 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new LinkedList<>();
    // 记录回溯算法的递归路径
    LinkedList<Integer> track = new LinkedList<>();


    // main
    public List<List<Integer>> combine(int n, int k) {
        backtrack(1,n, k);
        return res;
    }


    // backtrack algorithm
    void backtrack(int start, int n, int k) {
        // base case
        if (k==track.size()){
            res.add(new LinkedList<>(track));
            return;
        }

        // 回溯算法标准框架
        for (int i = start; i <= n; i++) {
            // choose
            track.addLast(i);
            // 通过 start 参数控制树枝的遍历，避免产⽣重复的⼦集
            backtrack(i+1,n,k);
            // revoke
            track.removeLast();
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
