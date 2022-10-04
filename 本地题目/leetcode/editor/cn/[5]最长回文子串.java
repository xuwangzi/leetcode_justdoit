//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 5749 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            // 以s[i]为中心的最长回文子串
            String str1 = longerPalindrome(s, i, i);

            // 以s[i] s[i+1]为中心的最长回文子串
            String str2 = longerPalindrome(s, i, i + 1);

            // longest
            res = res.length() > str1.length() ? res : str1;
            res = res.length() > str2.length() ? res : str2;

        }

        return res;

    }


    //    在s中寻找以s[left] s[right]为中心的最长回文串
    private String longerPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
