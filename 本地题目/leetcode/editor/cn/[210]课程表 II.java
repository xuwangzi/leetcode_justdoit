//现在你总共有 numCourses 门课需要选，记为 0 到 numCourses - 1。给你一个数组 prerequisites ，其中 
//prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。 
//
// 
// 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。 
// 
//
// 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：[0,1]
//解释：总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
// 
//
// 示例 2： 
//
// 
//输入：numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//输出：[0,2,1,3]
//解释：总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
//因此，一个正确的课程顺序是 [0,1,2,3] 。另一个正确的排序是 [0,2,1,3] 。 
//
// 示例 3： 
//
// 
//输入：numCourses = 1, prerequisites = []
//输出：[0]
// 
//
// 
//提示：
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// 所有[ai, bi] 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 726 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录后序遍历结果
    List<Integer> postorder = new ArrayList<>();
    // 记录⼀次递归堆栈中的节点
    boolean[] onPath;
    // 记录遍历过的节点，防⽌⾛回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;


    /* main */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建图（邻接表）
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        // init
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        // 遍历图中的所有节点
        for (int i = 0; i < numCourses; i++) {
            traverse(graph, i);
        }

        // 有循环依赖，无法完成所有课程
        if (hasCycle) return new int[]{};

        // 逆后序遍历结果即为拓扑排序结果
        // TODO: 2022/11/13
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }

        return res;
    }


    /* 建图函数 */
    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // init
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        // add edges
        for (int[] edge : prerequisites) {
            int from = edge[1], to = edge[0];
            // 添加⼀条从 from 指向 to 的有向边
            // 边的⽅向是「被依赖」关系，即修完课程 from 才能修课程 to
            graph[from].add(to);
        }

        return graph;
    }


    // 递归遍历
    void traverse(List<Integer>[] graph, int curr) {
        // 出现环
        if (onPath[curr]) hasCycle = true;

        // 已经遍历过该点 或者 找到环
        if (visited[curr] || hasCycle) return;

        /* 前序代码位置 */
        visited[curr] = true;
        onPath[curr] = true;

        /* 递归遍历 */
        for (int next : graph[curr]) {
            traverse(graph, next);
        }

        /* 后序代码位置 */
        postorder.add(curr);// TODO: 2022/11/13
        onPath[curr] = false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
