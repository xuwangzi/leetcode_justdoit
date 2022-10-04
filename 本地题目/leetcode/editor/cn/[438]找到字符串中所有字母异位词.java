//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1015 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
//        结果集
        List<Integer> res = new ArrayList<>();

//        hashmap
        HashMap<Character, Integer> need = new HashMap<Character, Integer>();
        HashMap<Character, Integer> window = new HashMap<Character, Integer>();

//        window
        int left=0,right=0;
        int valid=0;

//        need
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i),need.getOrDefault(p.charAt(i),0)+1);
        }

//        expand
        while(right<s.length()){
            char c = s.charAt(right);

            right++;

            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);

                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

//            shrink
            if (right-left>=p.length()){
//                judge
                if (valid==need.size()){
                    res.add(left);
                }

                char d = s.charAt(left);

                left++;

                if (need.containsKey(d)){
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }

                    window.put(d,window.getOrDefault(d,0)-1);
                }

            }

        }

        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
