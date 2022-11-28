//给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。 
//
// 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 10⁴ 。 
//
// 
//
// 示例 1： 
//
// 
//输入：expression = "2-1-1"
//输出：[0,2]
//解释：
//((2-1)-1) = 0 
//(2-(1-1)) = 2
// 
//
// 示例 2： 
//
// 
//输入：expression = "2*3-4*5"
//输出：[-34,-14,-10,-10,10]
//解释：
//(2*(3-(4*5))) = -34 
//((2*3)-(4*5)) = -14 
//((2*(3-4))*5) = -10 
//(2*((3-4)*5)) = -10 
//(((2*3)-4)*5) = 10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= expression.length <= 20 
// expression 由数字和算符 '+'、'-' 和 '*' 组成。 
// 输入表达式中的所有整数值在范围 [0, 99] 
// 
//
// Related Topics 递归 记忆化搜索 数学 字符串 动态规划 👍 767 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // memo
    HashMap<String, List<Integer>> memo = new HashMap<>();


    // main
    public List<Integer> diffWaysToCompute(String expression) {
        // check memo
        if (memo.containsKey(expression)) return memo.get(expression);

        // 分治(recursion)
        List<Integer> res = new LinkedList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '-' || c == '+' || c == '*') {
                // 分 (recursion)
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                // 治
                for (int a : left)
                    for (int b : right)
                        if (c == '+') res.add(a + b);
                        else if (c == '-') res.add(a - b);
                        else if (c == '*') res.add(a * b);

            }
        }

        // base case
        // 如果 res 为空，说明算式是⼀个数字，没有运算符
        if (res.isEmpty())
            res.add(Integer.parseInt(expression));


        // take memo
        memo.put(expression, res);

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
