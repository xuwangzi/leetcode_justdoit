//这里有 n 个航班，它们分别从 1 到 n 进行编号。 
//
// 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 
//firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。 
//
// 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。 
//
// 
//
// 示例 1： 
//
// 
//输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
//输出：[10,55,45,25,25]
//解释：
//航班编号        1   2   3   4   5
//预订记录 1 ：   10  10
//预订记录 2 ：       20  20
//预订记录 3 ：       25  25  25  25
//总座位数：      10  55  45  25  25
//因此，answer = [10,55,45,25,25]
// 
//
// 示例 2： 
//
// 
//输入：bookings = [[1,2,10],[2,2,15]], n = 2
//输出：[10,25]
//解释：
//航班编号        1   2
//预订记录 1 ：   10  10
//预订记录 2 ：       15
//总座位数：      10  25
//因此，answer = [10,25]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2 * 10⁴ 
// 1 <= bookings.length <= 2 * 10⁴ 
// bookings[i].length == 3 
// 1 <= firsti <= lasti <= n 
// 1 <= seatsi <= 10⁴ 
// 
//
// Related Topics 数组 前缀和 👍 404 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 差分数组工具类
class Difference {
    // 差分数组
    private int[] diff;

    // 构造函数：输入一个数组，构造对应的差分数组
    public Difference(int[] nums) {
        // 断言：nums必须非空
        assert nums.length > 0;

        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }

    // 闭区间[i,j] 增加val
    public void increment(int i, int j, int val) {
        diff[i] += val;
        if (j + 1 < diff.length) {
            diff[j + 1] -= val;
        }// else   此时 j=diff.length-1 ，闭区间右侧无需修改
    }

    // 返回结果
    public int[] getResult() {
        int[] res = new int[diff.length];

        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }

        return res;
    }

}


class Solution {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums= new int[n];

        Difference df = new Difference(nums);

        for (int[] booking :
                bookings) {
            int i = booking[0]-1;
            int j = booking[1]-1;
            int val = booking[2];

            df.increment(i,j,val);
        }

        return df.getResult();
    }

}
//leetcode submit region end(Prohibit modification and deletion)
