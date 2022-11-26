//给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：4
//解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
// 
//
// 示例 2： 
//
// 
//输入：n = 0
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：n = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 5 * 10⁶ 
// 
//
// Related Topics 数组 数学 枚举 数论 👍 979 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 数学算法 : Sieve of Eratosthenes
    public int countPrimes(int n) {
        // isPrime[0,1,...,n-1]
        boolean[] isPrime = new boolean[n  ];
        Arrays.fill(isPrime, true);

        // sieve
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // count
        int res = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) res++;
        }

        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
