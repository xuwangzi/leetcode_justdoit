//你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row,
// col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你
//每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。 
//
// 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。 
//
// 请你返回从左上角走到右下角的最小 体力消耗值 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
//输出：2
//解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
//这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
//输出：1
//解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
// 
//
// 示例 3： 
// 
// 
//输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
//输出：0
//解释：上图所示路径不需要消耗任何体力。
// 
//
// 
//
// 提示： 
//
// 
// rows == heights.length 
// columns == heights[i].length 
// 1 <= rows, columns <= 100 
// 1 <= heights[i][j] <= 10⁶ 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 323 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* main */
    // 输⼊⼀个⼆维矩阵，计算从左上⻆到右下⻆的最⼩体⼒消耗
    // Dijkstra 算法，计算 (0, 0) 到 (m - 1, n - 1) 的最⼩体⼒消耗
    public int minimumEffortPath(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        // 定义：从 (0, 0) 到 (i, j) 的最⼩体⼒消耗是 effortTo[i][j]
        int[][] effortTo = new int[m][n];
        // dp table 初始化为正⽆穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        // base case，起点到起点的最⼩消耗就是 0
        effortTo[0][0] = 0;
        // 优先级队列，effortFromStart 较⼩的排在前⾯
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });
        // 从起点 (0, 0) 开始进⾏ BFS
        pq.offer(new State(0, 0, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curEffortFromStart = curState.effortFromStart;

            // 到达终点提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromStart;
            }

            if (curEffortFromStart > effortTo[curX][curY]) {
                continue;
            }

            // 将 (curX, curY) 的相邻坐标装⼊队列
            for (int[] neighbor:adj(heights,curX,curY)){
                int nextX =neighbor[0];
                int nextY =neighbor[1];

                // 计算从 (0, 0) 达到 (nextX, nextY) 的消耗
                int effortToNextNode = Math.max(
                        effortTo[curX][curY],
                        Math.abs(heights[curX][curY]-heights[nextX][nextY])
                );

                // 更新 dp table
                if (effortTo[nextX][nextY]>effortToNextNode){
                    effortTo[nextX][nextY] = effortToNextNode;
                    pq.offer(new State(nextX,nextY,effortToNextNode));
                }
            }
        }

        // 正常情况不会达到这个 return
        return -1;
    }


    // State 类
    private class State {
        // 矩阵中的⼀个位置
        int x, y;
        // 从起点 (0, 0) 到当前位置的最⼩体⼒消耗（距离）
        int effortFromStart;

        State(int x, int y, int effortFromStart) {
            this.x = x;
            this.y = y;
            this.effortFromStart = effortFromStart;
        }
    }


    // ⽅向数组，上下左右的坐标偏移量
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // 返回坐标 (x, y) 的上下左右相邻坐标
    private List<int[]> adj(int[][] matrix, int x1, int y1) {
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();

        for (int[] dir : dirs) {
            int x2 = x1 + dir[0];
            int y2 = y1 + dir[1];

            // 确保索引不越界
            if (x2 >= 0 && x2 < matrix.length && y2 >= 0 && y2 < matrix[0].length) {
                neighbors.add(new int[]{x2, y2});
            }
        }

        return neighbors;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
