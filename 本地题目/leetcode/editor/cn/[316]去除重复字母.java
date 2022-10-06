//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 由小写英文字母组成 
// 
//
// 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-
//distinct-characters 相同 
//
// Related Topics 栈 贪心 字符串 单调栈 👍 826 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 要求一（不重复）、
     * 通过inStack这个布尔数组做到栈stk中不存在重复元素。
     * <p>
     * 要求二（顺序不变）、
     * 我们顺序遍历字符串s，通过「栈」这种顺序结构的 push/pop 操作记录结果字符串，保证了字符出现的顺序和s中出现的顺序一致。
     * 这里也可以想到为什么要用「栈」这种数据结构，因为先进后出的结构允许我们立即操作刚插入的字符，如果用「队列」的话肯定是做不到的。
     * <p>
     * 要求三（字典顺序最小）、
     * 我们用类似单调栈的思路，配合计数器count不断 pop 掉不符合最小字典序的字符，保证了最终得到的结果字典序最小。
     * <p>
     * 当然，由于栈的结构特点，我们最后需要把栈中元素取出后再反转一次才是最终结果。
     *
     * @param s
     * @return
     */

    public String removeDuplicateLetters(String s) {
        // 单调栈
        Stack<Character> stack = new Stack<>();

        // 维护一个计数器记录字符串中字符的数量
        // 因为输入为 ASCII字符，大小256够用了
        int[] count = new int[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        // 布尔数组：栈中无重复元素
        boolean[] inStack = new boolean[256];
        for (char c : s.toCharArray()) {
            // 每遍历过一个字符，都将对应的计数减一
            count[c]--;

            if (inStack[c]) continue;

            while(!stack.isEmpty()&&stack.peek()>c){
                if (count[stack.peek()]==0){
                    // 若之后不存在栈顶元素了，则停止 pop
                    break;
                }else {
                    // 若之后还有，则可以 pop
                    inStack[stack.pop()]=false;
                }
            }

            stack.push(c);
            inStack[c]=true;

        }

        // 栈 -> 最终结果
        StringBuilder sb = new StringBuilder();
        while (!stack.empty()){
            sb.append(stack.pop());
        }

        return sb.reverse().toString();

    }


}
//leetcode submit region end(Prohibit modification and deletion)
