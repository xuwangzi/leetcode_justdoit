//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 3533 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        // 使用 栈，存放左括号
        Stack<Character> left = new Stack();

        // loop
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                // 左括号入栈
                left.push(c);
            } else if (!left.empty() && leftOf(c) == left.peek()) {// java里Stack的peek方法是返回栈顶的元素但不移除它
                // 匹配到右括号，栈顶左括号出栈
                left.pop();// Stack的pop方法是会移除栈顶的元素的
            } else {
                return false;
            }
        }

        return left.empty();

    }

    char leftOf(char c) {
        if (c == '}') return '{';
        else if (c == ']') return '[';
        else if (c == ')') return '(';
        else return '^';
    }

}
//leetcode submit region end(Prohibit modification and deletion)
