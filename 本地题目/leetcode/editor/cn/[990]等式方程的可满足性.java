//给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!
//=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。 
//
// 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 输入：["a==b","b!=a"]
//输出：false
//解释：如果我们指定，a = 1 且 b = 1，那么可以满足第一个方程，但无法满足第二个方程。没有办法分配变量同时满足这两个方程。
// 
//
// 示例 2： 
//
// 输入：["b==a","a==b"]
//输出：true
//解释：我们可以指定 a = 1 且 b = 1 以满足满足这两个方程。
// 
//
// 示例 3： 
//
// 输入：["a==b","b==c","a==c"]
//输出：true
// 
//
// 示例 4： 
//
// 输入：["a==b","b!=c","c==a"]
//输出：false
// 
//
// 示例 5： 
//
// 输入：["c==c","b==d","x!=z"]
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= equations.length <= 500 
// equations[i].length == 4 
// equations[i][0] 和 equations[i][3] 是小写字母 
// equations[i][1] 要么是 '='，要么是 '!' 
// equations[i][2] 是 '=' 
// 
//
// Related Topics 并查集 图 数组 字符串 👍 268 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean equationsPossible(String[] equations) {
        // 26 个英⽂字⺟
        UF uf = new UF(26);

        // 先让相等的字⺟形成连通分量
        for (String eq:equations){
            if (eq.charAt(1)=='='){
                char x = eq.charAt(0);
                char y = eq.charAt(3);

                uf.union(x-'a',y-'a');
            }
        }

        // 检查不等关系是否打破相等关系的连通性
        for (String eq:equations){
            if (eq.charAt(1)=='!'){
                char x=eq.charAt(0);
                char y=eq.charAt(3);

                // 如果相等关系成⽴，就是逻辑冲突
                if (uf.connected(x-'a',y-'a')){
                    return false;
                }
            }
        }


        return true;

    }
}


//  Union Find 算法
class UF {
    // 连通分量个数
    private int count;
    // 存储每个节点的⽗节点
    private int[] parent;


    // n 为图中节点的个数
    public UF(int n) {
        count = n;
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }


    // 查找根节点，同时优化树，确保 树的深度=2（递归）
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);// 递归
        }

        return parent[x];
    }


    // 将节点 p 和节点 q 连通
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP != rootQ) {
            parent[rootP]=rootQ;

            // 两个连通分量合并成⼀个连通分量
            count--;
        }

        return;

    }


    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p,int q){
        return find(p) == find(q);
    }


    // 返回图中的连通分量个数
    public int getCount(){
        return count;
    }



}

//leetcode submit region end(Prohibit modification and deletion)
