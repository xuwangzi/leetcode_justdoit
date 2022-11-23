//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 2686 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    /* method-01 : 动态规划 + memo */
//    // memo
//    // define : memo[i][j] 记录 s1[0..i] 和 s2[0..j] 的最⼩编辑距离
//    int[][] memo;
//
//    // main
//    public int minDistance(String word1, String word2) {
//        int m = word1.length(), n = word2.length();
//
//        // init memo
//        memo = new int[m][n];
//        for (int i = 0; i < m; i++) {
//            Arrays.fill(memo[i], -1);
//        }
//
//        return dp(word1, m - 1, word2, n - 1);
//
//    }
//
//
//    // define : 返回 s1[0..i] 和 s2[0..j] 的最⼩编辑距离
//    int dp(String s1, int i, String s2, int j) {
//        // base case
//        if (i == -1) return j + 1;
//        if (j == -1) return i + 1;
//
//        // check memo
//        if (memo[i][j] != -1) return memo[i][j];
//
//        // state transition & take memo
//        if (s1.charAt(i) == s2.charAt(j)) {
//            memo[i][j] = dp(s1, i - 1, s2, j - 1);// skip
//        } else {
//            memo[i][j] = Math.min(
//                    dp(s1, i - 1, s2, j - 1) + 1,// replace s1[i] s2[j]
//                    Math.min(
//                            dp(s1, i - 1, s2, j) + 1,// delete s1[i]
//                            dp(s1, i, s2, j - 1) + 1// insert s1[i+1]
//                    )
//            );
//        }
//
//        return memo[i][j];
//
//    }


    /* method-02 : 动态规划 + dp table */
    // main
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // dp table
        // define : s1[0..i] 和 s2[0..j] 的最⼩编辑距离是 dp[i+1][j+1]
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }

        // bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            dp[i - 1][j - 1] + 1,
                            Math.min(
                                    dp[i][j - 1] + 1,
                                    dp[i - 1][j] + 1
                            )
                    );
                }
            }
        }

        return dp[m][n];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
