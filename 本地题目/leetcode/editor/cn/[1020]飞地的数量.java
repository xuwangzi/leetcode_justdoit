//给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。 
//
// 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。 
//
// 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
//输出：3
//解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
// 
//
// 示例 2： 
// 
// 
//输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
//输出：0
//解释：所有 1 都在边界上或可以到达边界。
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 500 
// grid[i][j] 的值为 0 或 1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 189 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numEnclaves(int[][] grid) {
        int res = 0;

        // 把靠边的岛屿淹掉
        for (int i = 0; i < grid.length; i++) {
            floodFill(grid, i, 0);
            floodFill(grid, i, grid[0].length - 1);
        }
        for (int j = 0; j < grid[0].length; j++) {
            floodFill(grid, 0, j);
            floodFill(grid, grid.length - 1, j);
        }

        // 遍历 grid，剩下的岛屿都是封闭岛屿
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res += 1;
                }
            }
        }

        return res;

    }


    // FloodFill 算法
    /// 从 (i, j) 开始，将与之相邻的陆地都变成海⽔
    void floodFill(int[][] grid, int i, int j) {
        // base case
        /// 已经是海⽔了
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        } else if (grid[i][j] == 0) {
            return;
        }

        // 将 (i, j) 变成海⽔
        grid[i][j] = 0;

        // 淹没上下左右的陆地
        floodFill(grid, i + 1, j);
        floodFill(grid, i - 1, j);
        floodFill(grid, i, j + 1);
        floodFill(grid, i, j - 1);

        return;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
