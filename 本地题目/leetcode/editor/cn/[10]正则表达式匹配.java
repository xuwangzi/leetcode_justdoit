//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 30 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
//
// Related Topics 递归 字符串 动态规划 👍 3329 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /* main : 计算 p[j..] 是否匹配 s[i..] */
    public boolean isMatch(String s, String p) {
        // init memo
        memo = new int[s.length()][p.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(s, 0, p, 0);
    }


    // memo
    int[][] memo;


    // dp
    boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();

        // base case
        if (j == n) return i == m;
        if (i == m) {
            if ((n - j) % 2 != 0) return false;

            for (int tmp = j; tmp + 1 < n; tmp += 2) {
                if (p.charAt(tmp + 1) != '*')
                    return false;
            }

            return true;
        }

        // check memo
        if (memo[i][j] != -1) return memo[i][j] == 0 ? false : true;

        // state transition
        boolean res = false;

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2)
                        || dp(s, i + 1, p, j);
            } else {
                res = dp(s, i + 1, p, j + 1);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2);
            } else {
                res = false;
            }
        }

        // take memo
        memo[i][j] = res ? 1 : 0;

        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
