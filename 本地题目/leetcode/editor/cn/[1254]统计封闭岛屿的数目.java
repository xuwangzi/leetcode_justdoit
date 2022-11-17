//二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。 
//
// 请返回 封闭岛屿 的数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,
//0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,1,1,1,1,1,1],
//             [1,0,0,0,0,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,1,0,1,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length, grid[0].length <= 100 
// 0 <= grid[i][j] <=1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 166 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int closedIsland(int[][] grid) {
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
                if (grid[i][j]==0){
                    res++;
                    floodFill(grid,i,j);
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
        } else if (grid[i][j] == 1) {
            return;
        }

        // 将 (i, j) 变成海⽔
        grid[i][j] = 1;

        // 淹没上下左右的陆地
        floodFill(grid, i + 1, j);
        floodFill(grid, i - 1, j);
        floodFill(grid, i, j + 1);
        floodFill(grid, i, j - 1);

    }


}
//leetcode submit region end(Prohibit modification and deletion)
