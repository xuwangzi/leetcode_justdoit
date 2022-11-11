//给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序） 
//
// 
// graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：graph = [[1,2],[3],[3],[]]
//输出：[[0,1,3],[0,2,3]]
//解释：有两条路径 0 -> 1 -> 3 和 0 -> 2 -> 3
// 
//
// 示例 2： 
//
// 
//
// 
//输入：graph = [[4,3,1],[3,2,4],[3],[4],[]]
//输出：[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// 
//
// 提示： 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i（即不存在自环） 
// graph[i] 中的所有元素 互不相同 
// 保证输入为 有向无环图（DAG） 
// 
//
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 👍 349 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录所有路径
    List<List<Integer>> res = new LinkedList<>();

    /* main */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        // 维护递归过程中经过的路径
        LinkedList<Integer> path = new LinkedList<>();

        // 遍历
        traverse(graph, 0, path);

        return res;
    }

    /* 图的遍历框架 */
    private void traverse(int[][] graph, int start, LinkedList<Integer> path) {
        // 添加节点 start 到路径
        path.addLast(start);

        int n = graph.length;
        if (start == n - 1) {// 到达终点
            /// 注意 Java 的语⾔特性：
            ///   因为 Java 函数参数传的是对象引⽤，所以向 res 中添加 path 时需要拷⻉⼀个新的列表，否则最终 res 中的列表都是空的。
            res.add(new LinkedList<>(path));

            path.removeLast();
            return;
        }

        // 递归每个相邻节点
        for (int nextNode:graph[start]){
            traverse(graph,nextNode,path);
        }

        // 从路径移出节点 s
        path.removeLast();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
