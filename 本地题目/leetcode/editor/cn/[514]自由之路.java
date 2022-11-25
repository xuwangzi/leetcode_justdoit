//电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。 
//
// 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。 
//
// 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按
//钮，以此逐个拼写完 key 中的所有字符。 
//
// 旋转 ring 拼出 key 字符 key[i] 的阶段中： 
//
// 
// 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于
//字符 key[i] 。 
// 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段
//）, 直至完成所有拼写。 
// 
//
// 
//
// 示例 1： 
//
// 
//
//
// 
//
//
// 
//输入: ring = "godding", key = "gd"
//输出: 4
//解释:
// 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。 
// 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
// 当然, 我们还需要1步进行拼写。
// 因此最终的输出是 4。
// 
//
// 示例 2: 
//
// 
//输入: ring = "godding", key = "godding"
//输出: 13
// 
//
// 
//
// 提示： 
//
// 
// 1 <= ring.length, key.length <= 100
// ring 和 key 只包含小写英文字母 
// 保证 字符串 key 一定可以由字符串 ring 旋转拼出 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 字符串 动态规划 👍 253 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // map : ring's char -> index(s)
    HashMap<Character, LinkedList<Integer>> map = new HashMap<>();


    // memo[i][j] : when ptr in ring[i], need memo[i][j] steps to key[j].
    int[][] memo;


    // main
    public int findRotateSteps(String ring, String key) {
        // init memo
        memo = new int[ring.length()][key.length()];
        for (int[] row : memo) Arrays.fill(row, -1);

        // init map
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);

            if (!map.containsKey(c)) {
                map.put(c, new LinkedList<Integer>());
            }
            map.get(c).add(i);
        }

        // dp
        return dp(ring, 0, key, 0);
    }


    // dp(String ring,int i,String key,int j) :
    //      when ptr in ring[i], need res steps to key[j].
    int dp(String ring, int i, String key, int j) {
        // base case
        if (j == key.length()) return 0;

        // check memo
        if (memo[i][j] != -1) return memo[i][j];

        // state transition
        int res = Integer.MAX_VALUE;
        for (int next : map.get(key.charAt(j))) {
            // min(anitclock, clockwise)
            int steps = Math.min(
                    Math.abs(next - i),
                    (ring.length() - Math.abs(next - i)));
            // dp
            int afterSteps = dp(ring, next, key, j + 1);

            res = Math.min(res, steps + afterSteps +1);
        }

        // take memo
        memo[i][j] = res;

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
