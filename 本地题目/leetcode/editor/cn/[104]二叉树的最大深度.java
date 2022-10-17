//给定一个二叉树，找出其最大深度。 
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例： 给定二叉树 [3,9,20,null,null,15,7]， 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最大深度 3 。 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 1407 👎 0


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
    // 遇到⼀道⼆叉树的题⽬时的通⽤思考过程是：
    // 1、是否可以通过遍历⼀遍⼆叉树得到答案？如果可以，⽤⼀个 traverse 函数配合外部变量来实现。
    // 2、是否可以定义⼀个递归函数，通过⼦问题（⼦树）的答案推导出原问题的答案？如果可以，写出这个递归函数的定义，并充分利⽤这个函数的返回值。


    /* 方法一：遍历 */
//    // 记录最⼤深度
//    int res = 0;
//    // 记录遍历到的节点的深度
//    int depth = 0;
//
//    // 主函数
//    public int maxDepth(TreeNode root) {
//        traverse(root);
//        return res;
//    }
//
//    // ⼆叉树遍历框架
//    void traverse(TreeNode root) {
//        // base case
//        if (root == null) return;
//
//        // preorder
//        depth++;
//
//        /// 到达叶⼦节点，更新最⼤深度
//        if (root.left == null && root.right == null) {
//            res = Math.max(res, depth);
//        }
//        /// 左子树
//        traverse(root.left);
//        /// 右子树
//        traverse(root.right);
//
//        // postorder
//        depth--;
//    }


    /* 方法二：递归 */
    public int maxDepth(TreeNode root) {
        // base case
        if(root==null) return 0;

        // 利⽤定义，计算左右⼦树的最⼤深度
        int leftMax = maxDepth(root.left);
        int rightMax = maxDepth(root.right);

        // 整棵树的最⼤深度等于左右⼦树的最⼤深度取最⼤值，然后再加上根节点⾃⼰
        int res = Math.max(leftMax,rightMax)+1;

        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
