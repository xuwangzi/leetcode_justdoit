//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//输入：n = 3
//输出：[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
// 
// 
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 树 二叉搜索树 动态规划 回溯 二叉树 👍 1340 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    /* 主函数 */
    public List<TreeNode> generateTrees(int n) {
        // special case
        if (n == 0) return new LinkedList<>();

        return build(1, n);
    }

    /* 构造闭区间 [lo, hi] 组成的 BST */
    List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();

        // base case
        if (lo > hi) {
            res.add(null);
            return res;
        }

        // 1、穷举 root 节点的所有可能。
        for (int valOfRoot = lo; valOfRoot <= hi; valOfRoot++) {
            // 2、递归构造出左右子树的所有合法 BST。
            List<TreeNode> leftTrees = build(lo, valOfRoot - 1);
            List<TreeNode> rightTrees = build(valOfRoot + 1, hi);

            // 3、给 root 节点穷举所有左右子树的组合。
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    // valOfRoot 作为根节点 root 的值
                    TreeNode root = new TreeNode(valOfRoot);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
