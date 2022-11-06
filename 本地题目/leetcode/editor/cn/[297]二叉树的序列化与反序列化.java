//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的
//方法解决这个问题。 
//
// 
//
// 示例 1： 
// 
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
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
//输入：root = [1]
//输出：[1]
// 
//
// 示例 4： 
//
// 
//输入：root = [1,2]
//输出：[1,2]
// 
//
// 
//
// 提示： 
//
// 
// 树中结点数在范围 [0, 10⁴] 内 
// -1000 <= Node.val <= 1000 
// 
//
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 1012 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    /**
     * 方法一：前序遍历解法（后序类似）
     */
//    String SEP_SIGN = ",";
//    String NULL_SIGN = "#";
//
//    // Encodes a tree to a single string.
//    public String serialize(TreeNode root) {
//        StringBuilder sb = new StringBuilder();
//        serialize(root, sb);
//        return sb.toString();
//    }
//
//    void serialize(TreeNode root, StringBuilder sb) {
//        // base case
//        if (root == null) {
//            sb.append(NULL_SIGN).append(SEP_SIGN);
//            return;
//        }
//
//        /****** 前序遍历位置 ******/
//        sb.append(root.val).append(SEP_SIGN);
//        /***********************/
//
//        serialize(root.left, sb);
//        serialize(root.right, sb);
//    }
//
//
//    // Decodes your encoded data to tree.
//    public TreeNode deserialize(String data) {
//        // String -> List
//        LinkedList<String> nodes = new LinkedList<>();
//        for (String s : data.split(SEP_SIGN)) {
//            nodes.addLast(s);
//        }
//
//        return deserialize(nodes);
//    }
//
//    TreeNode deserialize(LinkedList<String> nodes) {
//        // base case
//        if (nodes.isEmpty()) return null;
//
//        /****** 前序遍历位置 ******/
//        // 列表最左侧就是根节点
//        String rootStr = nodes.removeFirst();
//        if (rootStr.equals(NULL_SIGN)) return null;
//        TreeNode root = new TreeNode(Integer.parseInt(rootStr));
//        /***********************/
//
//        root.left = deserialize(nodes);
//        root.right = deserialize(nodes);
//
//        return root;
//    }


    /**
     * 方法二：层级遍历解法
     */
    String SEP_SIGN = ",";
    String NULL_SIGN = "#";

    public String serialize(TreeNode root) {
        // special case
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            TreeNode cur = q.poll();

            /*** 层级遍历代码位置 ***/
            if (cur==null){
                sb.append(NULL_SIGN).append(SEP_SIGN);
                continue;
            }

            sb.append(cur.val).append(SEP_SIGN);
            /*********************/

            q.offer(cur.left);
            q.offer(cur.right);
        }

        return sb.toString();
    }


    public TreeNode deserialize(String data) {
        // sepcial case
        if (data.isEmpty())return null;

        LinkedList<String> nodes = new LinkedList<>();
        for (String s: data.split(SEP_SIGN)){
            nodes.addLast(s);
        }

        // 第一个元素就是 root 的值
        // 队列 q 记录父节点，将 root 加入队列
        TreeNode root =
                new TreeNode(Integer.parseInt(nodes.removeFirst()));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);


        while(!q.isEmpty()){
            TreeNode parent = q.poll();

            // parent.left
            String left = nodes.poll();
            if (left.equals(NULL_SIGN)){
                parent.left = null;
            }else {
                parent.left = new TreeNode(Integer.parseInt(left));
                q.offer(parent.left);
            }
            // parent.right
            String right = nodes.poll();
            if (right.equals(NULL_SIGN)){
                parent.right = null;
            }else {
                parent.right = new TreeNode(Integer.parseInt(right));
                q.offer(parent.right);
            }

        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
