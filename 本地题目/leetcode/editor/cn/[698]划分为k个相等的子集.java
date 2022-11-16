//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 
//
// 示例 1： 
//
// 
//输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 示例 2: 
//
// 
//输入: nums = [1,2,3,4], k = 3
//输出: false 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 每个元素的频率在 [1,4] 范围内 
// 
//
// Related Topics 位运算 记忆化搜索 数组 动态规划 回溯 状态压缩 👍 859 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // 排除⼀些基本情况
        /// 1
        if (k > nums.length) return false;
        /// 2
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;

        int used = 0; // 使⽤位图技巧
        int target = sum / k;
        // k 号桶初始什么都没装，从 nums[0] 开始做选择
        return backtrack(k, 0, nums, 0, used, target);
    }


    /**
     * backtrack 函数的参数定义：
     * 现在 k 号桶正在思考是否应该把 nums[start] 这个元素装进来；
     * ⽬前 k 号桶⾥⾯已经装的数字之和为 bucket；
     * used 标志某⼀个元素是否已经被装到桶中；
     * target 是每个桶需要达成的⽬标和。
     */
    private HashMap<Integer, Boolean> memo = new HashMap<>();

    private boolean backtrack(int k, int bucket, int[] nums,int start, int used, int target) {
        // base case
        if (k == 0) return true;// 所有桶都被装满了，⽽且 nums ⼀定全部⽤完了

        if (bucket == target) {
            // 装满了当前桶，递归穷举下⼀个桶的选择
            // 让下⼀个桶从 nums[0] 开始选数字
            boolean res = backtrack(k - 1, 0, nums,0, used, target);

            // 缓存结果
            memo.put(used, res);
            return res;
        }

        if (memo.containsKey(used)) {
            // 避免冗余计算
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {
            // 剪枝
            if (((used >> i) & 1) == 1) {// 判断第 i 位是否是 1
                // nums[i] 已经被装⼊别的桶中
                continue;
            }
            if (nums[i]+bucket>target){
                continue;
            }

            // 做选择
            used |= (1<<i);// 将第 i 位置为 1
            bucket += nums[i];

            // 递归穷举下⼀个数字是否装⼊当前桶
            if (backtrack(k,bucket,nums,i+1,used,target)){
                return true;
            }

            // 撤销选择
            used ^= (1<<i); // 使⽤异或运算将第 i 位恢复 0
            bucket -= nums[i];

        }


        return false;

    }


}
//leetcode submit region end(Prohibit modification and deletion)
