//给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。 
//
// 有效 二叉搜索树定义如下： 
//
// 
// 节点的左子树只包含 小于 当前节点的数。 
// 节点的右子树只包含 大于 当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [2,1,3]
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：root = [5,1,4,null,null,3,6]
//输出：false
//解释：根节点的值是 5 ，但是右子节点的值是 4 。
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目范围在[1, 10⁴] 内 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1808 👎 0


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
    /* method 1 */
//    public boolean isValidBST(TreeNode root) {
//        return isValidBST(root,null,null);
//    }
//
//    boolean isValidBST(TreeNode root,TreeNode min,TreeNode max){
//        // base case
//        if (root==null) return true;
//
//        if (min!=null && root.val<=min.val) return false;
//        if (max!=null && root.val>=max.val) return false;
//
//        return isValidBST(root.left,min,root) && isValidBST(root.right,root,max);
//    }


    /* method 2 : TODO 待优化！*/
    private long pre = Long.MIN_VALUE;
    private boolean res = true;


    public boolean isValidBST(TreeNode root) {
        traverse(root);

        return res;
    }


    public void traverse(TreeNode root) {
        // base case
        if (res == false || root == null) return;

        traverse(root.left);

        if ((long)root.val > pre) {
            pre = (long)root.val;
        } else {
            res = false;
        }

        boolean rightIsValid = isValidBST(root.right);

        return;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
