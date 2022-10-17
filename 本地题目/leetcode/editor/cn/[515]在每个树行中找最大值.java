//给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。 
//
// 
//
// 示例1： 
//
// 
//
// 
//输入: root = [1,3,2,5,3,null,9]
//输出: [1,3,9]
// 
//
// 示例2： 
//
// 
//输入: root = [1,2,3]
//输出: [1,3]
// 
//
// 
//
// 提示： 
//
// 
// 二叉树的节点个数的范围是 [0,10⁴] 
// 
// -2³¹ <= Node.val <= 2³¹ - 1 
// 
//
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 279 👎 0


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
    // 层序遍历
    public List<Integer> largestValues(TreeNode root) {

        // 记录结果
        List<Integer> res = new ArrayList<>();
        // 队列 数据结构
        Queue<TreeNode> q = new LinkedList<>();
        // 记录 最大数
        int max = Integer.MIN_VALUE;

        // null
        if (root == null) return res;

        // 从上到下遍历二叉树的每⼀层
        q.offer(root);
        while (!q.isEmpty()) {// while 循环：从上到下 遍历
            int sz = q.size();
            for (int i = 0; i < sz; i++) {// for 循环：从左到右 遍历
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }

                // 比较 最大值
                max = cur.val > max ? cur.val : max;
            }

            // 记录 最大值
            res.add(max);
            // 重置 最大值
            max = Integer.MIN_VALUE;
        }

        return res;
    }



}
//leetcode submit region end(Prohibit modification and deletion)
