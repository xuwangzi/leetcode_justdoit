//f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。 
//
// 
// 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。 
// 
//
// 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：k = 0
//输出：5
//解释：0!, 1!, 2!, 3!, 和 4! 均符合 k = 0 的条件。
// 
//
// 示例 2： 
//
// 
//输入：k = 5
//输出：0
//解释：没有匹配到这样的 x!，符合 k = 5 的条件。 
//
// 示例 3: 
//
// 
//输入: k = 3
//输出: 5
// 
//
// 
//
// 提示: 
//
// 
// 0 <= k <= 10⁹ 
// 
//
// Related Topics 数学 二分查找 👍 195 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // main
    public int preimageSizeFZF(int k) {
        // binarySearch [left_bound,right_bound]
        // 有效区间一定存在
        long left = leftBound(k);
        long right = rightBound(k, left);

        return (int) (right - left + 1);
    }


    // binarySearch [left_bound,right_bound]
    long leftBound(long target) {
        // [lo,hi)
        long lo = 0, hi = Long.MAX_VALUE;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {// trailingZeroes(mid)==target
                hi = mid;
            }
        }

        return lo;

    }

    long rightBound(long target, long left) {
        // [lo,hi)
        long lo = left, hi = Long.MAX_VALUE;

        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;

            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return hi - 1;

    }


    // 计算 n! 的结尾有几个0
    public long trailingZeroes(long n) {
        long count = 0;
        for (long i = 5; i <= n; i *= 5) {
            count += n / i;
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
