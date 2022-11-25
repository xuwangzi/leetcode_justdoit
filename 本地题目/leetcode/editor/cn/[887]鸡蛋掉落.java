//给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。 
//
// 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。 
//
// 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎
//，则可以在之后的操作中 重复使用 这枚鸡蛋。 
//
// 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？ 
//
// 示例 1： 
//
// 
//输入：k = 1, n = 2
//输出：2
//解释：
//鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。 
//否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。 
//如果它没碎，那么肯定能得出 f = 2 。 
//因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。 
// 
//
// 示例 2： 
//
// 
//输入：k = 2, n = 6
//输出：3
// 
//
// 示例 3： 
//
// 
//输入：k = 3, n = 14
//输出：4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= 100 
// 1 <= n <= 10⁴ 
// 
//
// Related Topics 数学 二分查找 动态规划 👍 896 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // memo[egg:1-k][floor:1-n]
    int[][] memo;


    // main
    public int superEggDrop(int k, int n) {

        // init
        memo = new int[k + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        // dp
        return dp(k, n);

    }


    // dp
    int dp(int k, int n) {
        // return times;
        int times = Integer.MAX_VALUE;

        // base case
        if (k == 1) return n;
        if (n == 0) return 0;

        // check memo
        if (memo[k][n] != -1) return memo[k][n];

        // state transition
        /* 未使用 binarySearch 优化：时间=O(KN^2) 空间=O(KN) */
//        for (int floor = 1; floor <= n; floor++) {
//            // this floor
//            int timesOfThisFloor = Math.max(
//                    dp(k - 1, floor - 1),// broken
//                    dp(k, n - floor ))// not broken
//                    + 1;
//
//            // res
//            times = Math.min(times, timesOfThisFloor);
//        }
        /* binarySearch 优化 state transition：时间=O(KNlogN) 空间=O(KN)
         *  查找 Math.max(dp(k - 1, floor - 1), dp(k, n - floor )) 的 Vally 值
        * */
        int lo = 1, hi = n;// [lo,hi]
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int broken = dp(k - 1, mid - 1);
            int not_broken = dp(k, n - mid);
            if (broken > not_broken) {
                hi = mid - 1;
                times = Math.min(times, broken + 1);
            }else {
                lo = mid+1;
                times =Math.min(times,not_broken+1);
            }
        }

        // take memo
        memo[k][n] = times;

        return  memo[k][n];

    }

}
//leetcode submit region end(Prohibit modification and deletion)
