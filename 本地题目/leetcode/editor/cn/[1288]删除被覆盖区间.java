//给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。 
//
// 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。 
//
// 在完成所有删除操作后，请你返回列表中剩余区间的数目。 
//
// 
//
// 示例： 
//
// 
//输入：intervals = [[1,4],[3,6],[2,8]]
//输出：2
//解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 1000 
// 0 <= intervals[i][0] < intervals[i][1] <= 10^5 
// 对于所有的 i != j：intervals[i] != intervals[j] 
// 
//
// Related Topics 数组 排序 👍 87 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // main
    public int removeCoveredIntervals(int[][] intervals) {
        // sort intervals :
        //      start in ascend, if start is equal, end in descend
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];// start - ascend
            } else {
                return b[1] - a[1];// end - descend
            }
        });

        // 记录合并区间的起点和终点
        int left = intervals[0][0], right = intervals[0][1];
        // 记录被覆盖的区间数
        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];

            // case 1 : 找到覆盖区间，记录
            // left————————————————————right
            //       cur[0]————cur[1]
            if (left <= cur[0] && right >= cur[1]) {
                res++;
            }

            // case 2 : 找到相交区间，更新起点和终点
            // left——————————————right
            //        cur[0]——————————————cur[1]
            // case 3 : 完全不相交，更新起点和终点
            // left————right
            //                 cur[0]————cur[1]
            if (
                    (right >= cur[0] && right <= cur[1]) // case2
                            || (right < cur[1]) // case3
            ) {
                left = cur[0];
                right = cur[1];
            }

        }


        return intervals.length - res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
