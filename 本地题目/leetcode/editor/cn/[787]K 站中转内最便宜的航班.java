//有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城
//市 fromi 开始，以价格 pricei 抵达 toi。 
//
// 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便
//宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。 
//
// 
//
// 示例 1： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 1
//输出: 200
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。 
//
// 示例 2： 
//
// 
//输入: 
//n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
//src = 0, dst = 2, k = 0
//输出: 500
//解释: 
//城市航班图如下
//
//
//从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 100 
// 0 <= flights.length <= (n * (n - 1) / 2) 
// flights[i].length == 3 
// 0 <= fromi, toi < n 
// fromi != toi 
// 1 <= pricei <= 10⁴ 
// 航班没有重复，且不存在自环 
// 0 <= src, dst, k < n 
// src != dst 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列） 👍 540 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

class Solution {
    // State 类
    class State {
        // 图节点的 id
        int id;
        // 从 src 节点到当前节点的花费
        int costFromSrc;
        // 从 src 节点到当前节点经过的节点个数
        int nodeNumFromSrc;

        State(int id, int costFromSrc, int nodeNumFromSrc) {
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.nodeNumFromSrc = nodeNumFromSrc;
        }
    }


    // main
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // 建立 邻接表
        List<int[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : flights) {
            int from = edge[0];
            int to = edge[1];
            int price = edge[2];
            graph[from].add(new int[]{to, price});
        }

        // 启动 dijkstra 算法
        // 计算以 src 为起点在 k 次中转到达 dst 的最短路径
        K++;
        return dijkstra(graph, src, K, dst);
    }


    // 输⼊⼀个起点 src，计算从 src 到其他节点的最短距离
    int dijkstra(List<int[]>[] graph, int src, int k, int dst) {
        // 定义：从起点 src 到达节点 i 的最短路径权重为 distTo[i]
        int[] distTo = new int[graph.length];
        // 定义：从起点 src 到达节点 i ⾄少要经过 nodeNumTo[i] 个节点
        int[] nodeNumTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        Arrays.fill(nodeNumTo, Integer.MAX_VALUE);
        // base case
        distTo[src] = 0;
        nodeNumTo[src] = 0;
        // 优先级队列，costFromSrc 较⼩的排在前⾯
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.costFromSrc - b.costFromSrc;
        });
        // 从起点 src 开始进⾏ BFS
        pq.offer(new State(src, 0, 0));
        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeID = curState.id;
            int costFromSrc = curState.costFromSrc;
            int curNodeNumFromSrc = curState.nodeNumFromSrc;

            if (curNodeID == dst) {
                // 找到最短路径
                return costFromSrc;
            }
            if (curNodeNumFromSrc == k) {
                // 中转次数耗尽
                continue;
            }
            // 将 curNode 的相邻节点装⼊队列
            for (int[] neighbor : graph[curNodeID]) {
                int nextNodeID = neighbor[0];
                int costToNextNode = costFromSrc + neighbor[1];
                // 中转次数消耗 1
                int nextNodeNumFromSrc = curNodeNumFromSrc + 1;
                // 更新 dp table
                if (distTo[nextNodeID] > costToNextNode) {
                    distTo[nextNodeID] = costToNextNode;
                    nodeNumTo[nextNodeID] = nextNodeNumFromSrc;
                }
                // 剪枝，如果中转次数更多，花费还更⼤，那必然不会是最短路径
                if (costToNextNode > distTo[nextNodeID]
                        && nextNodeNumFromSrc > nodeNumTo[nextNodeID]) {
                    continue;
                }

                pq.offer(new State(nextNodeID, costToNextNode,
                        nextNodeNumFromSrc));
            }
        }
        return -1;
    }


}


//* Time Limit Exceeded 超时！！！ */
//class Solution {
//    // memo[u][v][w] : src=i,dst=j,steps=w(=k+1)
//    /// case(-1) : 没有记录
//    /// case(Integer.MAX_VALUE) : 此路不通
//    int[][][] memo;
//    /// sign for memo
//    int NO_RECORD = -2;
//    int NEVER_REACH = -1;
//
//    // neighbor table : graph
//    LinkedList<int[]>[] table;
//
//
//    // main
//    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
//        // init
//        /// steps
//        int steps = k + 1;
//        /// memo
//        memo = new int[n][n][steps + 1];
//        for (int[][] i : memo) {
//            for (int[] j : i) {
//                Arrays.fill(j, NO_RECORD);
//            }
//        }
//        /// visited[from][to] : make sure no cycle
//        /// !!! 点可以重复走，边不可以重复走
//        boolean[][] visited = new boolean[n][n];
//        /// table
//        table = new LinkedList[n];
//        for (int i = 0; i < n; i++) {
//            table[i] = new LinkedList<int[]>();
//        }
//        for (int[] edge : flights) {
//            int from = edge[0];
//            int to = edge[1];
//            int cost = edge[2];
//            table[from].add(new int[]{to, cost});
//        }
//
//        // dp
//        return dp(table, src, dst, steps, visited);
//    }
//
//
//    // dp
//    int dp(LinkedList<int[]>[] table, int src, int dst, int steps, boolean[][] visited) {
//
//        // base case
//        if (src == dst) {
//            return 0;// reach
//        } else if (steps == 0) {
//            return NEVER_REACH;// never reach
//        }
//
//        // check memo
//        if (memo[src][dst][steps] != NO_RECORD && memo[src][dst][steps] != NEVER_REACH) {
//            return memo[src][dst][steps];// target memo
//        }
//
//        int res = NEVER_REACH;
//
//        // state transition
//        for (int[] e : table[src]) {
//            int next = e[0], cost = e[1];
//
//            if (!visited[src][next]) {
//                visited[src][next] = true;
//
//                int next_cost = dp(table, next, dst, steps - 1, visited);
//                if (next_cost != NEVER_REACH) {
//                    if (res == NEVER_REACH) {
//                        res = cost + next_cost;
//                    } else {
//                        res = Math.min(res, cost + next_cost);
//                    }
//                }
//
//                visited[src][next] = false;
//            }
//
//        }
//
//        // take memo
//        memo[src][dst][steps] = res;
//
//        // 如果不存在这样的路线，则输出 -1
//        return res;
//
//    }
//
//
//
//}


//leetcode submit region end(Prohibit modification and deletion)
