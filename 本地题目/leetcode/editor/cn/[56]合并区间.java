//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 1722 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        // sort intervals :
        //      start in ascend
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];// start - ascend
        });

        // traversal
        int[][] res = new int[intervals.length][2];
        int count = 0;// 记录结果的条数
        for (int[] interval : intervals) {
            if (count == 0) {// base case
                res[count++] = interval;
            } else if (interval[0] > res[count-1][1]) {// 不重叠，结果中加一条
                res[count++] = interval;
            } else {// 重叠，合并
                res[count-1][1] = Math.max(res[count-1][1], interval[1]);
            }
        }

        return Arrays.copyOf(res, count);

    }


}
//leetcode submit region end(Prohibit modification and deletion)
