//有 n 个网络节点，标记为 1 到 n。 
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， 
//wi 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 👍 611 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* main */
    // times 记录边和权重，n 为节点个数（从 1 开始），k 为起点
    // 计算从 k 发出的信号⾄少需要多久传遍整幅图
    public int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要⼀个⼤⼩为 n + 1 的邻接表
        List<int[]>[] graph = buildGraph(times, n);

        // dijkstra 算法：计算以节点 k 为起点到其他节点的最短路径
        int[] distTo = dijkstra(k, graph);

        // 找到最⻓的那⼀条最短路径
        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            // 有节点不可达，返回 -1
            if (distTo[i] == Integer.MAX_VALUE) {
                return -1;
            }

            res = Math.max(res, distTo[i]);
        }


        return res;

    }


    /* 构造图 */
    private List<int[]>[] buildGraph(int[][] times, int n) {
        // 节点编号是从 1 开始的，所以要⼀个⼤⼩为 n + 1 的邻接表
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }

        // 构造图
        for (int[] edge : times) {
            int from = edge[0], to = edge[1], weight = edge[2];

            // from -> List<(to, weight)>
            // 邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to, weight});
        }

        return graph;
    }


    /* Dijkstra 算法 */
    // 输⼊⼀个起点 start，计算从 start 到其他节点的最短距离
    private int[] dijkstra(int start, List<int[]>[] graph) {
        // 定义：distTo[i] 的值就是起点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);// Integer.MAX_VALUE 表示 不连通
        // base case：start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列，distFromStart 较⼩的排在前⾯
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        // 从起点 start 开始进⾏ BFS
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curId = curState.id;
            int curDistFromStart = curState.distFromStart;

            if (curDistFromStart > distTo[curId]) {
                continue;
            }

            // 将 curNode 的相邻节点装⼊队列
            for (int[] neighbor : graph[curId]) {
                int nextId = neighbor[0];
                int distToNext = distTo[curId] + neighbor[1];

                // 更新 dp table：
                // 如果目前的这条路径更短，则将已经记录的路径舍弃
                if (distTo[nextId] > distToNext) {
                    distTo[nextId] = distToNext;
                    pq.offer(new State(nextId, distToNext));
                }
            }
        }


        return distTo;

    }


    // State 辅助类
    class State {
        // 图节点的 id
        int id;
        // 从 start 节点到当前节点的距离
        int distFromStart;

        public State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)
