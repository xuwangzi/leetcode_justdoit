//给定两个整数数组，preorder 和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵
//树的后序遍历，重构并返回二叉树。 
//
// 如果存在多个答案，您可以返回其中 任何 一个。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
//输出：[1,2,3,4,5,6,7]
// 
//
// 示例 2: 
//
// 
//输入: preorder = [1], postorder = [1]
//输出: [1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= preorder.length <= 30 
// 1 <= preorder[i] <= preorder.length 
// preorder 中所有值都 不同 
// postorder.length == preorder.length 
// 1 <= postorder[i] <= postorder.length 
// postorder 中所有值都 不同 
// 保证 preorder 和 postorder 是同一棵二叉树的前序遍历和后序遍历 
// 
//
// Related Topics 树 数组 哈希表 分治 二叉树 👍 283 👎 0


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
    HashMap<Integer,Integer> valToIndex = new HashMap<>();


    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            valToIndex.put(postorder[i],i);
        }

        return build(preorder,0,preorder.length-1,
                postorder,0,postorder.length-1);
    }


    TreeNode build(int[] preorder,int preStart,int preEnd,
                   int[] postorder,int postStart,int postEnd){
        // base case
        if (postStart>postEnd) return null;
        // special case
        if (postStart == postEnd) return new TreeNode(preorder[preStart]);
        /// 当 postStart == postEnd 时，root 不存在 左右子树

        // root
        int rootVal = preorder[preStart];
        // leftRootVal
        int leftRootVal = preorder[preStart+1];
        int index = valToIndex.get(leftRootVal);// leftRoot在后序遍历数组中的索引

        // build root
        TreeNode root = new TreeNode(rootVal);
        // build left/right
        int leftSize = index-postStart+1;
        root.left = build(preorder,preStart+1,preStart+leftSize,
                postorder, postStart,index);
        root.right = build(preorder,preStart+leftSize+1,preEnd,
                postorder,index+1,postEnd-1);

        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
