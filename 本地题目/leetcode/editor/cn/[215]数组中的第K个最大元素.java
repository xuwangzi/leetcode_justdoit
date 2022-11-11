//给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。 
//
// 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。 
//
// 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。 
//
// 
//
// 示例 1: 
//
// 
//输入: [3,2,1,5,6,4], k = 2
//输出: 5
// 
//
// 示例 2: 
//
// 
//输入: [3,2,3,1,2,4,5,5,6], k = 4
//输出: 4 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 1951 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    /* 方法一：二叉堆 */
//    public int findKthLargest(int[] nums, int k) {
//        // 小顶堆，堆顶是最小元素
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//
//        for (int n : nums) {
//            // 每个元素都要过一遍二叉堆
//            pq.offer(n);
//
//            // 堆中元素多于 k 个时，删除堆顶元素
//            if (pq.size()>k){
//                pq.poll();
//            }
//        }
//
//        return pq.peek();
//    }

    /* 方法二：快速排序（优于二叉堆） */
    public int findKthLargest(int[] nums, int k) {
        // 首先随机打乱数组
        shuffle(nums);

        int lo=0,hi=nums.length-1;
        // 转化成「排名第 k 的元素」
        k = nums.length-k;
        while(lo<=hi){
            int p = partition(nums,lo,hi);

            if (p<k) lo=p+1;// 第 k 大的元素在 nums[p+1..hi] 中
            else if (p>k) hi=p-1;// 第 k 大的元素在 nums[lo..p-1] 中
            else return nums[p];// 找到第 k 大元素
        }

        return -1;
    }

    // 对 nums[lo..hi] 进行切分
    private int partition(int[] nums, int lo, int hi) {
        int pivot = nums[lo];

        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 我这里把 指针i和j 定义为开区间，同时定义：
        // [lo, i) <= pivot < (j, hi]
        // 之后都要正确维护这个边界区间的定义
        int i = lo + 1, j = hi;
        // 当 i > j 时结束循环，以保证区间 [lo, hi] 都被覆盖
        while (i <= j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] > pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] <= pivot
            }
            // 此时 [lo, i) <= pivot && (j, hi] > pivot

            if (i>=j){
                break;
            }

            swap(nums, i, j);
        }

        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, lo, j);
        return j;

    }

    // 洗牌算法，将输入的数组随机打乱
    private void shuffle(int[] nums) {
        Random rand = new Random();

        int len = nums.length;
        for (int i = 0; i < len; i++) {
            // 生成 [i, len - 1] 的随机数
            int r = i + rand.nextInt(len - i);// rand.nextInt(n) = [0,n-1]
            swap(nums, i, r);
        }
    }

    // 原地交换数组中的两个元素
    private void swap(int[] nums, int p1, int p2) {
        int tmp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = tmp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
