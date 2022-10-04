//车上最初有 capacity 个空座位。车 只能 向一个方向行驶（也就是说，不允许掉头或改变方向） 
//
// 给定整数 capacity 和一个数组 trips , trip[i] = [numPassengersi, fromi, toi] 表示第 i 次旅行有
// numPassengersi 乘客，接他们和放他们的位置分别是 fromi 和 toi 。这些位置是从汽车的初始位置向东的公里数。 
//
// 当且仅当你可以在所有给定的行程中接送所有乘客时，返回 true，否则请返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：trips = [[2,1,5],[3,3,7]], capacity = 4
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：trips = [[2,1,5],[3,3,7]], capacity = 5
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= trips.length <= 1000 
// trips[i].length == 3 
// 1 <= numPassengersi <= 100 
// 0 <= fromi < toi <= 1000 
// 1 <= capacity <= 10⁵ 
// 
//
// Related Topics 数组 前缀和 排序 模拟 堆（优先队列） 👍 207 👎 0


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
    public boolean carPooling(int[][] trips, int capacity) {
        // 0 <= fromi < toi <= 1000
        // 即 最多有1001个车站
        int[] nums =new int[1001];

        Difference df = new Difference(nums);

        for (int[] trip :
                trips) {
            // 第 trip[1] 站乘客上⻋，
            // 第 trip[2] 站乘客已经下⻋，
            // 即乘客在⻋上的区间是 [trip[1], trip[2] - 1]。
            int val=trip[0];
            int i=trip[1];
            int j=trip[2]-1;

            df.increment(i,j,val);
        }

        int[] res= df.getResult();

        for (int i = 0; i < res.length; i++) {
            if(res[i]>capacity){
                return false;
            }
        }

        return true;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
