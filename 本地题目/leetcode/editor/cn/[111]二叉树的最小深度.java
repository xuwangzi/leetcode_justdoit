//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 10⁵] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 876 👎 0


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
    // BFS
    public int minDepth(TreeNode root) {
        // special case
        if (root == null) return 0;

        // 核⼼数据结构
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        // root 本身就是⼀层，depth 初始化为 1
        int depth = 1;

        while (!q.isEmpty()) {// while————深度
            int sz = q.size();
            // 将当前队列中的所有节点向四周扩散
            for (int i = 0; i < sz; i++) {// for————广度
                TreeNode cur = q.poll();

                // 判断是否到达终点
                if (cur.left == null && cur.right == null) {
                    return depth;
                }

                // 将 cur 的相邻节点加⼊队列
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }

            // 这⾥增加步数
            depth++;

        }

        return depth;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
