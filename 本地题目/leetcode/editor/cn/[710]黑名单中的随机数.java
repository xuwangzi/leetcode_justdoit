//给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 
//blacklist 的整数。任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。 
//
// 优化你的算法，使它最小化调用语言 内置 随机函数的次数。 
//
// 实现 Solution 类: 
//
// 
// Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数 
// int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数 
// 
//
// 
//
// 示例 1： 
//
// 
//输入
//["Solution", "pick", "pick", "pick", "pick", "pick", "pick", "pick"]
//[[7, [2, 3, 5]], [], [], [], [], [], [], []]
//输出
//[null, 0, 4, 1, 6, 1, 0, 4]
//
//解释
//Solution solution = new Solution(7, [2, 3, 5]);
//solution.pick(); // 返回0，任何[0,1,4,6]的整数都可以。注意，对于每一个pick的调用，
//                 // 0、1、4和6的返回概率必须相等(即概率为1/4)。
//solution.pick(); // 返回 4
//solution.pick(); // 返回 1
//solution.pick(); // 返回 6
//solution.pick(); // 返回 1
//solution.pick(); // 返回 0
//solution.pick(); // 返回 4
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 10⁹ 
// 0 <= blacklist.length <= min(10⁵, n - 1) 
// 0 <= blacklist[i] < n 
// blacklist 中所有值都 不同 
// pick 最多被调用 2 * 10⁴ 次 
// 
//
// Related Topics 哈希表 数学 二分查找 排序 随机化 👍 212 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int sz;// sz 是 白名单个数。将白名单移到[0,sz-1]，将黑名单移到[sz,n-1]。
    HashMap<Integer, Integer> mapping;// 黑名单 -> [sz,n-1]


    public Solution(int n, int[] blacklist) {
        this.sz = n - blacklist.length;
        this.mapping=new HashMap<>();
        for(int b:blacklist){
            this.mapping.put(b,1);// 先将黑名单加入 mapping，便于之后的查找
        }

        // 尾指针
        int last = n - 1;

        for (int b : blacklist) {
            if (b >= sz) {
                continue;// 只需要处理[0,sz-1]区间的黑名单
            }else {
                // 找到 最后一个 白名单
                while (mapping.containsKey(last)){
                    last--;
                }

                // 黑名单 -> 最后一个 白名单
                mapping.put(b,last);//添加一个键值对，如果key已存在就覆盖，且返回被覆盖的
                // 尾指针移动
                last--;
            }
        }

    }


    public int pick() {
        Random r = new Random();
        int index = r.nextInt(sz);// nextInt()返回一个伪随机数，[0,n)之间

        if (mapping.containsKey(index)) {
            // 若命中⿊名单，则返回 mapping 中，黑名单 -> [sz,n-1]
            return mapping.get(index);
        } else {
            // 若没命中⿊名单，则直接返回
            return index;
        }

    }


}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(n, blacklist);
 * int param_1 = obj.pick();
 */
//leetcode submit region end(Prohibit modification and deletion)
