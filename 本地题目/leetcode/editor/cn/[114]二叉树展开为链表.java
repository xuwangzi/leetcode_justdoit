//给你二叉树的根结点 root ，请你将它展开为一个单链表： 
//
// 
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。 
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
// 
//
// 示例 2： 
//
// 
//输入：root = []
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [0]
//输出：[0]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 2000] 内 
// -100 <= Node.val <= 100 
// 
//
// 
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？ 
//
// Related Topics 栈 树 深度优先搜索 链表 二叉树 👍 1324 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    /* method 2: ⽤「分解问题」的思维模式 */
    // 思考过程：
    // 1. 给出 flatten 函数的定义：
    //      输⼊节点 root，然后 root 为根的二叉树就会被拉平为⼀条链表
    //      void flatten(TreeNode root);
    //
    // 2. 有了这个函数定义，如何按题⽬要求把⼀棵树拉平成⼀条链表？
    //      对于⼀个节点 x，可以执⾏以下流程：
    //      i. 先利⽤ flatten(x.left) 和 flatten(x.right) 将 x 的左右⼦树拉平。
    //      ii.将 x 的右⼦树接到左⼦树下⽅，然后将整个左⼦树作为右⼦树。
    //
    // 这就是递归的魅⼒，不容易说清楚 flatten 函数是怎么把左右⼦树拉平的，
    // 但是只要知道 flatten 的定义如此并利⽤这个定义，让每⼀个节点做它该做的事情，然后flatten 函数就会按照定义⼯作。
    public void flatten(TreeNode root) {
        // base case
        if (root==null) return;

        // 利⽤定义，把左右⼦树拉平
        flatten(root.left);
        flatten(root.right);

        /* 后序遍历位置 */
        // 1、左右⼦树已经被拉平成⼀条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        // 2、将左⼦树作为右⼦树
        root.left = null;
        root.right = left;
        // 3、将原先的右⼦树接到当前右⼦树的末端
        TreeNode p = root;
        while(p.right!=null){
            p=p.right;
        }
        p.right = right;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
