//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1981 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // main
    public int numIslands(char[][] grid) {
        int res = 0;

        // 遍历 grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    // 每发现⼀个岛屿，岛屿数量加⼀
                    res++;
                    // 然后使⽤ DFS 将岛屿淹了
                    floodFill(grid, i, j);
                }
            }
        }

        return res;

    }


    // FloodFill 算法
    /// 从 (i, j) 开始，将与之相邻的陆地都变成海⽔
    void floodFill(char[][] grid, int i, int j) {
        // base case
        /// 已经是海⽔了
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) {
            return;
        } else if (grid[i][j] == '0') {
            return;
        }

        // 将 (i, j) 变成海⽔
        grid[i][j] = '0';

        // 淹没上下左右的陆地
        floodFill(grid, i+1, j);
        floodFill(grid, i-1, j);
        floodFill(grid, i, j+1);
        floodFill(grid, i, j-1);

    }

}
//leetcode submit region end(Prohibit modification and deletion)
