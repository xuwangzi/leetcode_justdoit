//给定两个单词 word1 和
// word2 ，返回使得
// word1 和 
// word2 相同所需的最小步数。 
//
// 每步 可以删除任意一个字符串中的一个字符。 
//
// 
//
// 示例 1： 
//
// 
//输入: word1 = "sea", word2 = "eat"
//输出: 2
//解释: 第一步将 "sea" 变为 "ea" ，第二步将 "eat "变为 "ea"
// 
//
// 示例 2: 
//
// 
//输入：word1 = "leetcode", word2 = "etco"
//输出：4
// 
//
// 
//
// 提示： 
// 
//
// 
// 1 <= word1.length, word2.length <= 500 
// word1 和 word2 只包含小写英文字母 
// 
//
// Related Topics 字符串 动态规划 👍 509 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // memo
    int[][] memo;


    // main
    public int minDistance(String word1, String word2) {
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
        if (p1 == word1.length() || p2 == word2.length()) {
            return (p1 == word1.length()) ?
                    (word2.length() - p2) : (word1.length() - p1);
        }

        // check memo
        if (memo[p1][p2] != -1) return memo[p1][p2];

        // state transition & take memo
        if (word1.charAt(p1) == word2.charAt(p2)) {
            // skip
            memo[p1][p2] = dp(word1, p1 + 1, word2, p2 + 1);
        } else {
            // at least delete one ( word1[i] or word2[j] )
            memo[p1][p2] = 1 + Math.min(
                    dp(word1, p1 + 1, word2, p2),
                    dp(word1, p1, word2, p2 + 1)
            );
        }

        return memo[p1][p2];

    }


}
//leetcode submit region end(Prohibit modification and deletion)
