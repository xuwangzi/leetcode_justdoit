//返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。 
//
// 注意：该题与 316 https://leetcode.com/problems/remove-duplicate-letters/ 相同 
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
// 1 <= s.length <= 1000 
// s 由小写英文字母组成 
// 
//
// Related Topics 栈 贪心 字符串 单调栈 👍 168 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String smallestSubsequence(String s) {
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
