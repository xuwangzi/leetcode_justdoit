//给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。 
//
// 整数除法仅保留整数部分。 
//
// 你可以假设给定的表达式总是有效的。所有中间结果将在 [-2³¹, 2³¹ - 1] 的范围内。 
//
// 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "3+2*2"
//输出：7
// 
//
// 示例 2： 
//
// 
//输入：s = " 3/2 "
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：s = " 3+5 / 2 "
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 3 * 10⁵ 
// s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开 
// s 表示一个 有效表达式 
// 表达式中的所有整数都是非负整数，且在范围 [0, 2³¹ - 1] 内 
// 题目数据保证答案是一个 32-bit 整数 
// 
//
// Related Topics 栈 数学 字符串 👍 625 👎 0

/**
 * 772. 基本计算器 III
 * <p>
 * 实现一个基本的计算器来计算简单的表达式字符串。
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。整数除法需要 向下截断 。
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围为 [-2^31, 2^31 - 1] 。
 * <p>
 * 示例 1：
 * 输入：s = "1+1"
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：s = "6-4/2"
 * 输出：4
 * <p>
 * 示例 3：
 * 输入：s = "2*(5+5*2)/3+(6/2+8)"
 * 输出：21
 * <p>
 * 示例 4：
 * 输入：s = "(2+6*3+5-(3*14/7+2)*5)+3"
 * 输出：-12
 * <p>
 * 示例 5：
 * 输入：s = "0"
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * 1 <= s <= 10^4
 * s 由整数、'+'、'-'、'*'、'/'、'(' 和 ')' 组成
 * s 是一个 有效的 表达式
 */


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 基本计算器实现步骤：
     * ⼀、字符串转整数（注意 “整型溢出”）
     * ⼆、处理加减法 & 空格
     * 三、处理乘除法
     * 四、处理括号（使用 递归 ）
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        // 栈
        Stack<Integer> stk = new Stack<>();
        // 记录算式中的数字
        int num = 0;
        // 记录 num 前的符号，初始化为 +
        char sign = '+';

        // loop
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // 如果是数字，连续读取到 num
                num = 10 * num + (c - '0');// (c - '0') 的这个括号不能省略，否则可能造成整型溢出。
            }

            if (c == '(') {
                // 遇到 ”(“ 开始递归
                // 找到 对应的 ”)“
                int j = findClosing(s.substring(i));
                // 递归
                num = calculate(s.substring(i + 1, i + j));
                // 将 i 指向 对应的 ”)“
                i += j;
            }

            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                // 如果不是数字和空格，就是遇到了下⼀个符号，
                // 之前的数字和符号就要存进栈中
                int pre;

                switch (sign) {
                    case '+':
                        stk.push(num);
                        break;
                    case '-':
                        stk.push(-num);
                        break;
                    // 对于 × 和 ÷ ，只要拿出前⼀个数字做对应运算即可
                    case '*':
                        pre = stk.pop();
                        stk.push(pre * num);
                        break;
                    case '/':
                        pre = stk.pop();
                        stk.push(pre / num);
                        break;
                }

                // 更新符号为当前符号，数字清零
                sign = c;
                num = 0;
            }

        }

        // 将栈中所有结果求和就是答案
        int res = 0;
        while (!stk.isEmpty()) {
            res += stk.pop();
        }


        return res;

    }


    //删除所有的括号对，并返回右括号的位置
    private int findClosing(String s) {
        int level = 0, i = 0;
        for (i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                level++;
            } else if (s.charAt(i) == ')') {
                level--;
                if (level == 0) break;
            }
        }
        return i;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
