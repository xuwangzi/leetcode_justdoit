//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 
//o(n) 时间内解决此问题的算法吗？
//
// Related Topics 哈希表 字符串 滑动窗口 👍 2167 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        // HashMap
        // need 需要凑⻬的字符，对应t
        // window 窗⼝中的字符
        HashMap<Character,Integer> need = new HashMap<Character,Integer>();
        HashMap<Character,Integer> window = new HashMap<Character,Integer>();

        // 初始化need
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        // 滑动窗口
        // 区间 [left, right) 是左闭右开的，初始情况下窗⼝没有包含任何元素
        int left = 0, right = 0;
        // valid 变量 表示窗⼝中满⾜ need 条件的字符个数
        int valid = 0;

        // 最⼩覆盖⼦串 的 起始索引 及 ⻓度
        // 将len设为最大整数，方便第一次比较的时候进行更新
        int start = 0, len =  Integer.MAX_VALUE;// len = 0x3f3f3f3f

        while (right < s.length()) {
            // 一、扩大窗口
            // c 是 将移⼊窗⼝的字符
            char c = s.charAt(right);
            // 扩⼤窗⼝
            right++;
            // 进⾏窗⼝内数据的⼀系列更新（。。。）
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))){
                    valid++;
                }
            }

            // 判断左侧窗⼝是否要收缩
            while (valid == need.size()) {
                // 二、收缩窗口
                // 在这⾥更新最⼩覆盖⼦串（。。。）
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d 是将移出窗⼝的字符
                char d = s.charAt(left);
                // 缩⼩窗⼝
                left++;
                // 进⾏窗⼝内数据的⼀系列更新（。。。）
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))){
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        // 返回最⼩覆盖⼦串
        return len ==  Integer.MAX_VALUE ?
                "" : s.substring(start, start + len);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
