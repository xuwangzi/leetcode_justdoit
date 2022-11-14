//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1454 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录⼀次递归堆栈中的节点
    boolean[] onPath;
    // 记录遍历过的节点，防⽌⾛回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;


    /* main */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 建图（邻接表）
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        // init
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        // 遍历图中的所有节点
        for (int i=0;i<numCourses;i++){
            traverse(graph,i);
        }

        // 只要没有循环依赖可以完成所有课程
        return !hasCycle;
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
    void traverse(List<Integer>[] graph,int curr){
        // 出现环
        if (onPath[curr]) hasCycle=true;

        // 已经遍历过该点 或者 找到环
        if (visited[curr] || hasCycle) return;

        /* 前序代码位置 */
        visited[curr]=true;
        onPath[curr]=true;

        /* 递归遍历 */
        for (int next:graph[curr]){
            traverse(graph,next);
        }

        /* 后序代码位置 */
        onPath[curr] = false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
