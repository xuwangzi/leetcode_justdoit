//存在一个 无向图 ，图中有 n 个节点。其中每个节点都有一个介于 0 到 n - 1 之间的唯一编号。给你一个二维数组 graph ，其中 graph[u]
// 是一个节点数组，由节点 u 的邻接节点组成。形式上，对于 graph[u] 中的每个 v ，都存在一条位于节点 u 和节点 v 之间的无向边。该无向图同时具有
//以下属性：
//
// 
// 不存在自环（graph[u] 不包含 u）。 
// 不存在平行边（graph[u] 不包含重复值）。 
// 如果 v 在 graph[u] 内，那么 u 也应该在 graph[v] 内（该图是无向图） 
// 这个图可能不是连通图，也就是说两个节点 u 和 v 之间可能不存在一条连通彼此的路径。 
// 
//
// 二分图 定义：如果能将一个图的节点集合分割成两个独立的子集 A 和 B ，并使图中的每一条边的两个节点一个来自 A 集合，一个来自 B 集合，就将这个图称
//为 二分图 。 
//
// 如果图是二分图，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
//输出：false
//解释：不能将节点分割成两个独立的子集，以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。 
//
// 示例 2： 
// 
// 
//输入：graph = [[1,3],[0,2],[1,3],[0,2]]
//输出：true
//解释：可以将节点分成两组: {0, 2} 和 {1, 3} 。 
//
// 
//
// 提示： 
//
// 
// graph.length == n 
// 1 <= n <= 100 
// 0 <= graph[u].length < n 
// 0 <= graph[u][i] <= n - 1 
// graph[u] 不会包含 u 
// graph[u] 的所有值 互不相同 
// 如果 graph[u] 包含 v，那么 graph[v] 也会包含 u 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 424 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录图是否符合⼆分图性质
    private boolean ok = true;
    // 记录图中节点的颜⾊，false 和 true 代表两种不同颜⾊
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;


    /* main : 输⼊邻接表，判断是否是⼆分图 */
    public boolean isBipartite(int[][] graph) {
        // init
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];

        // 因为图不⼀定是联通的，可能存在多个⼦图
        // 所以要把每个节点都作为起点进⾏⼀次遍历
        // 如果发现任何⼀个⼦图不是⼆分图，整幅图都不算⼆分图
        for (int v = 0; v < n; v++) {
            traverse(graph,v);
        }

        return ok;
    }


    // DFS 遍历框架
    private void traverse(int[][] graph,int v){
        // 如果已经确定不是⼆分图了，就不⽤浪费时间再递归遍历了
        if (!ok) return;
        // v 已经遍历过
        if (visited[v]) return;

        // else
        visited[v] = true;
        for (int w:graph[v]){
            if (!visited[w]){
                // 相邻节点 w 没有被访问过
                // 那么应该给节点 w 涂上和节点 v 不同的颜⾊
                color[w] = !color[v];

                // 继续遍历 w
                traverse(graph,w);
            }else {
                // 相邻节点 w 已经被访问过
                // 根据 v 和 w 的颜⾊判断是否是⼆分图
                if (color[w]==color[v]){
                    // 若相同，则此图不是⼆分图
                    ok=false;
                }
            }
        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)
