//在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（
//上下左右）进行交换. 
//
// 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。 
//
// 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：board = [[1,2,3],[4,0,5]]
//输出：1
//解释：交换 0 和 5 ，1 步完成
// 
//
// 示例 2: 
//
// 
//
// 
//输入：board = [[1,2,3],[5,4,0]]
//输出：-1
//解释：没有办法完成谜板
// 
//
// 示例 3: 
//
// 
//
// 
//输入：board = [[4,1,2],[5,0,3]]
//输出：5
//解释：
//最少完成谜板的最少移动次数是 5 ，
//一种移动路径:
//尚未移动: [[4,1,2],[5,0,3]]
//移动 1 次: [[4,1,2],[0,5,3]]
//移动 2 次: [[0,1,2],[4,5,3]]
//移动 3 次: [[1,0,2],[4,5,3]]
//移动 4 次: [[1,2,0],[4,5,3]]
//移动 5 次: [[1,2,3],[4,5,0]]
// 
//
// 
//
// 提示： 
//
// 
// board.length == 2 
// board[i].length == 3 
// 0 <= board[i][j] <= 5 
// board[i][j] 中每个值都 不同 
// 
//
// Related Topics 广度优先搜索 数组 矩阵 👍 286 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int m = 2;
    int n = 3;

    // 记录⼀维字符串的相邻索引
    int[][] neighbor = new int[][]{
            {1, 3},
            {0, 4, 2},
            {1, 5},
            {0, 4},
            {3, 1, 5},
            {4, 2}
    };

    // main : BFS
    public int slidingPuzzle(int[][] board) {
        // input -> String
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String input = sb.toString();
        // target
        String target = "123450";
        // Queue
        Queue<String> q = new LinkedList<>();
        q.offer(input);
        // visited
        Set<String> visited = new HashSet<>();
        visited.add(input);
        // steps
        int steps = 0;


        // deep
        while (!q.isEmpty()) {
            // breath
            int sz = q.size();
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                // judge
                /// target
                if (cur.equals(target)) {
                    return steps;
                }

                for (String next : getNexts(cur)) {
                    // judge
                    /// isNotVisited
                    if (!visited.contains(next)) {
                        q.offer(next);
                        visited.add(next);
                    }
                }

            }

            // deeper
            steps++;
        }


        return -1;

    }


    // cur -> nexts
    LinkedList<String> getNexts(String cur) {
        // res
        LinkedList<String> res = new LinkedList<>();

        // getIndexOf0
        int idx0 = 0;
        for (; cur.charAt(idx0) != '0'; idx0++) ;

        // swap 0 <-> neighbors
        for (int adj : neighbor[idx0]) {
            res.add(swap(cur.toCharArray(), adj, idx0));
        }

        return res;

    }


    String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }


//
//
//    // cur -> nexts
//    LinkedList<String> getNexts(String cur) {
//        // result
//        LinkedList<String> res = new LinkedList<>();
//
//        // getIndexOf0
//        int[] indexOf0 = getIndexOf0(cur);
//        int x0 = indexOf0[0];
//        int y0 = indexOf0[1];
//
//        // getNextIndex
//        for (
//                int[] nextIndex : new int[][]{{x0 - 1, y0}, {x0 + 1, y0}, {x0, y0 - 1}, {x0, y0 + 1}}
//        ) {
//            if (nextIndex[0] >= 0 && nextIndex[0] < m && nextIndex[1] >= 0&& nextIndex[1] < n) {
//                res.add(swap(cur, indexOf0, nextIndex));
//            }
//        }
//
//        return res;
//    }
//
//
//    // getIndexOf0
//    int[] getIndexOf0(String cur) {
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (cur.charAt(i * n + j) == '0') {
//                    return new int[]{i, j};
//                }
//            }
//        }
//
//        // never reach
//        return new int[]{-1, -1};
//    }
//
//
//    // swap char
//    String swap(String cur, int[] indexOf0, int[] nextIndex) {
//        char[] chs = cur.toCharArray();
//        chs[indexOf0[0] * n + indexOf0[1]] = chs[nextIndex[0] * n + nextIndex[1]];
//        chs[nextIndex[0] * n + nextIndex[1]]='0';
//
//        return chs.toString();
//    }


}
//leetcode submit region end(Prohibit modification and deletion)
