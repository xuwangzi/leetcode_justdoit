//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 19 
// 
//
// Related Topics 树 二叉搜索树 数学 动态规划 二叉树 👍 1991 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 备忘录：消除重叠子问题
    int[] memo;// memo[n] = n 个 node 组成的 BST 个数

    /* 主函数（递归） */
    public int numTrees(int n) {
        // 备忘录的值初始化为 0
        memo = new int[n+1];// 记录 [0,n] 个 node 组成的 BST 个数

        // 计算闭区间 [1, n] 组成的 BST 个数
        return count(n);// n 个 node
    }

    /* 计算 n 个 node 组成的 BST 个数 */
    int count(int n) {
        // base case
        if (n == 0) return 1;

        // 查备忘录
        if (memo[n]!=0) return memo[n];

        int res = 0;
        for (int valOfRoot = 1; valOfRoot <= n; valOfRoot++) {
            // valOfRoot 的值作为根节点 root
            int left = count(valOfRoot - 1);
            int right = count(n - valOfRoot);

            // 左右子树的组合数乘积是 BST 的总数
            res += left * right;
        }

        // 将结果存入备忘录
        memo[n] = res;

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
