//珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。 
//
// 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后
//这一小时内不会再吃更多的香蕉。 
//
// 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。 
//
// 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：piles = [3,6,7,11], h = 8
//输出：4
// 
//
// 示例 2： 
//
// 
//输入：piles = [30,11,23,4,20], h = 5
//输出：30
// 
//
// 示例 3： 
//
// 
//输入：piles = [30,11,23,4,20], h = 6
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= piles.length <= 10⁴ 
// piles.length <= h <= 10⁹ 
// 1 <= piles[i] <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 436 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * binarySearch
 * f(x)
 * h
 * |    \
 * |     \______
 * |     ↑      \
 * |             \
 * --------------------- x
 */
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // [left,right]
        // 1 <= piles[i] <= 10⁹
        int left = 1;
        int right = (int) 1e9;

        // binarySearch
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (f(piles, mid) < h) {
                right = mid - 1;
            } else if (f(piles, mid) > h) {
                left = mid + 1;
            } else {// f(piles, mid) == h
                right = mid - 1;
            }

        }

        return left;

    }


    // 以x的速度，需要f(x)小时吃完
    /*
    注意：
        测试用例中，可能会出现int溢出的情况，使用long可以解决
        相关测试用例：piles = [805306368,805306368,805306368]
                        h = 1000000000
     */
    long f(int[] piles, int x) {
        long hours = 0;

        for (int pile :
                piles) {
            hours += pile / x;
            if (pile % x > 0) {
                hours++;
            }
        }

        return hours;

    }


}

//leetcode submit region end(Prohibit modification and deletion)
