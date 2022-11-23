//给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。 
//
// 
//
// 示例 1: 
//
// 
//输入: s1 = "sea", s2 = "eat"
//输出: 231
//解释: 在 "sea" 中删除 "s" 并将 "s" 的值(115)加入总和。
//在 "eat" 中删除 "t" 并将 116 加入总和。
//结束时，两个字符串相等，115 + 116 = 231 就是符合条件的最小和。
// 
//
// 示例 2: 
//
// 
//输入: s1 = "delete", s2 = "leet"
//输出: 403
//解释: 在 "delete" 中删除 "dee" 字符串变成 "let"，
//将 100[d]+101[e]+101[e] 加入总和。在 "leet" 中删除 "e" 将 101[e] 加入总和。
//结束时，两个字符串都等于 "let"，结果即为 100+101+101+101 = 403 。
//如果改为将两个字符串转换为 "lee" 或 "eet"，我们会得到 433 或 417 的结果，比答案更大。
// 
//
// 
//
// 提示: 
//
// 
// 0 <= s1.length, s2.length <= 1000 
// s1 和 s2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 301 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // memo
    int[][] memo;


    // main
    public int minimumDeleteSum(String word1, String word2) {
        int m = word1.length(), n = word2.length();

        // init memo
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // dp
        return dp(word1, 0, word2, 0);
    }


    // dp
    // define : word[p,...] minDistance
    int dp(String word1, int p1, String word2, int p2) {

        // base case
        if (p1 == word1.length()) {
            int sumOfRest = 0;
            for (int p = p2; p < word2.length(); p++) {
                sumOfRest +=word2.charAt(p);
            }
            return sumOfRest;
        }
        if (p2 == word2.length()) {
            int sumOfRest = 0;
            for (int p = p1; p < word1.length(); p++) {
                sumOfRest +=  word1.charAt(p);
            }
            return sumOfRest;
        }

        // check memo
        if (memo[p1][p2] != -1) return memo[p1][p2];

        // state transition & take memo
        if (word1.charAt(p1) == word2.charAt(p2)) {
            // skip
            memo[p1][p2] = dp(word1, p1 + 1, word2, p2 + 1);
        } else {
            // at least delete one ( word1[i] or word2[j] )
            memo[p1][p2] = Math.min(
                   word1.charAt(p1) + dp(word1, p1 + 1, word2, p2),
                   word2.charAt(p2) + dp(word1, p1, word2, p2 + 1)
            );
        }

        return memo[p1][p2];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
