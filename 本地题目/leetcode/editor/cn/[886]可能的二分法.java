//给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。 
//
// 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和 bi的人归入同一组。当可以用
//这种方法将所有人分进两组时，返回 true；否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
// 
//
// 示例 2： 
//
// 
//输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2000 
// 0 <= dislikes.length <= 10⁴ 
// dislikes[i].length == 2 
// 1 <= dislikes[i][j] <= n 
// ai < bi 
// dislikes 中每一组都 不同 
// 
//
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 347 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录图是否符合⼆分图性质
    private boolean ok = true;
    // 记录图中节点的颜⾊，false 和 true 代表两种不同颜⾊
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;


    /* main */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        // init
        // 图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        // 转化成邻接表表示图结构
        List<Integer>[] graph = buildGraph(n, dislikes);

        // 图节点编号为 1...n
        for (int v = 1; v <= n; v++) {
            traverse(graph, v);
        }

        return ok;

    }


    // 建图函数（邻接表）
    private List<Integer>[] buildGraph(int n, int[][] dislike) {
        // 图节点编号为 1...n
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : dislike) {
            int v = edge[0];
            int w = edge[1];

            // v -> w
            graph[v].add(w);
            // w -> v
            graph[w].add(v);
        }

        return graph;
    }


    // DFS 遍历框架
    private void traverse(List<Integer>[] graph, int v) {
        // 如果已经确定不是⼆分图了，就不⽤浪费时间再递归遍历了
        if (!ok) return;
        // v 已经遍历过
        if (visited[v]) return;

        // else
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                // 相邻节点 w 没有被访问过
                // 那么应该给节点 w 涂上和节点 v 不同的颜⾊
                color[w] = !color[v];

                // 继续遍历 w
                traverse(graph, w);
            } else {
                // 相邻节点 w 已经被访问过
                // 根据 v 和 w 的颜⾊判断是否是⼆分图
                if (color[w] == color[v]) {
                    // 若相同，则此图不是⼆分图
                    ok = false;
                }
            }
        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)
