//给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。 
//
// 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 
//val 的绝对值。 
//
// 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
//输出：20
//解释：
//
//我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
//注意到任意两个点之间只有唯一一条路径互相到达。
// 
//
// 示例 2： 
//
// 
//输入：points = [[3,12],[-2,5],[-4,1]]
//输出：18
// 
//
// 示例 3： 
//
// 
//输入：points = [[0,0],[1,1],[1,0],[-1,1]]
//输出：4
// 
//
// 示例 4： 
//
// 
//输入：points = [[-1000000,-1000000],[1000000,1000000]]
//输出：4000000
// 
//
// 示例 5： 
//
// 
//输入：points = [[0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= points.length <= 1000 
// -10⁶ <= xi, yi <= 10⁶ 
// 所有点 (xi, yi) 两两不同。 
// 
//
// Related Topics 并查集 数组 最小生成树 👍 248 👎 0


//leetcode submit region begin(Prohibit modification and deletion)


///* main : 方法一 Kruskal 算法 */
//class Solution {
//    public int minCostConnectPoints(int[][] points) {
//        int n = points.length;
//
//        // 生成所有边及权重
//        List<int[]> edges = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                int xi = points[i][0], yi = points[i][1];
//                int xj = points[j][0], yj = points[j][1];
//                int Manhattan = Math.abs(xi - xj) + Math.abs(yi - yj);
//                // 用坐标点在 points 中的索引表示坐标点
//                edges.add(new int[]{
//                        i, j, Manhattan
//                });
//            }
//        }
//
//
//        // Kruskal 算法
//        Kruskal kruskal = new Kruskal(edges, n);
//        return kruskal.getMST();
//
//    }
//
//
//}
//
//
//// Kruskal 算法
//class Kruskal {
//
//    List<int[]> edges = new ArrayList<>();
//    int n;
//
//    public Kruskal(List<int[]> edges, int n) {
//        this.edges = edges;
//        this.n = n;
//    }
//
//    /* Kruskal 算法 */
//    public int getMST() {
//        int mst = 0;// 最⼩⽣成树 min spanning tree
//        UF uf = new UF(n);
//
//        // 将边按照权重从小到大排序
//        Collections.sort(edges, (a, b) -> {
//            return a[2] - b[2];
//        });
//
//        for (int[] edge : edges) {
//            int v = edge[0], u = edge[1];
//            int weight = edge[2];
//
//            // 若这条边会产生环，则不能加入 mst
//            // 若这条边不会产生环，则属于最小生成树
//            if (!uf.connected(u, v)) {
//                mst += weight;
//                uf.union(u, v);
//            }
//        }
//
//
//        return mst;
//    }
//
//}
//
//
//// Union Find 算法
//class UF {
//    // 连通分量个数
//    private int count;
//    // 存储每个节点的⽗节点
//    private int[] parent;
//
//
//    // n 为图中节点的个数
//    public UF(int n) {
//        count = n;
//        parent = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            parent[i] = i;
//        }
//    }
//
//
//    // 查找根节点，同时优化树，确保 树的深度=2（递归）
//    public int find(int x) {
//        if (parent[x] != x) {
//            parent[x] = find(parent[x]);// 递归
//        }
//
//        return parent[x];
//    }
//
//
//    // 将节点 p 和节点 q 连通
//    public void union(int p, int q) {
//        int rootP = find(p);
//        int rootQ = find(q);
//
//        if (rootP != rootQ) {
//            parent[rootP] = rootQ;
//
//            // 两个连通分量合并成⼀个连通分量
//            count--;
//        }
//
//        return;
//
//    }
//
//
//    // 判断节点 p 和节点 q 是否连通
//    public boolean connected(int p, int q) {
//        return find(p) == find(q);
//    }
//
//
//    // 返回图中的连通分量个数
//    public int getCount() {
//        return count;
//    }
//
//
//}


/* main : 方法二 Prim 算法 */
class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]>[] graph = new LinkedList[n];

        // 构造⽆向图
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];
                int Manhattan = Math.abs(xi - xj) + Math.abs(yi - yj);
                // 用坐标点在 points 中的索引表示坐标点
                graph[i].add(new int[]{i,j,Manhattan});
                graph[j].add(new int[]{j,i,Manhattan});
            }
        }


        // Prim 算法
        return new Prim(graph).getWeightSum();

    }


}


// Prim 算法
class Prim {
    // 核⼼数据结构，存储「横切边」的优先级队列
    private PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
        return a[2] - b[2];// 按照边的权重从⼩到⼤排序
    });
    // 类似 visited 数组的作⽤，记录哪些节点已经成为最⼩⽣成树的⼀部分
    private boolean[] inMST;
    // 记录最⼩⽣成树的权重和
    private int weightSum = 0;
    // graph 是⽤邻接表表示的⼀幅图，
    // graph[s] 记录节点 s 所有相邻的边，
    // 三元组 int[]{from, to, weight} 表示⼀条边
    private List<int[]>[] graph;


    public Prim(List<int[]>[] graph) {
        this.graph = graph;
        // 图中有 n 个节点
        int n = graph.length;
        this.inMST = new boolean[n];

        // 随便从⼀个点开始切分都可以，我们不妨从节点 0
        inMST[0] = true;
        cut(0);// 将 0 的横切边加⼊优先队列
        // 不断进⾏切分，向最⼩⽣成树中添加边
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int to = edge[1];
            int weight = edge[2];

            // 节点 to 不在最⼩⽣成树中
            if (!inMST[to]) {
                // 将边 edge 加⼊最⼩⽣成树
                weightSum += weight;
                inMST[to] = true;

                // 节点 to 加⼊后，进⾏新⼀轮切分，会产⽣更多横切边
                cut(to);
            }
        }

    }


    // 将 s 的横切边加⼊优先队列
    private void cut(int s) {
        // 遍历 s 的邻边
        for (int[] edge : graph[s]) {
            int to = edge[1];

            // 节点 to 不在最⼩⽣成树中
            if (!inMST[to]) {
                // 加⼊横切边队列
                pq.offer(edge);
            }
        }
    }


    // 最⼩⽣成树的权重和
    public int getWeightSum() {
        return weightSum;
    }


    // 判断最⼩⽣成树是否包含图中的所有节点
    public boolean allConnected() {
        for (int i = 0; i < inMST.length; i++) {
            if (!inMST[i]) return false;
        }

        return true;
    }


}


//leetcode submit region end(Prohibit modification and deletion)
