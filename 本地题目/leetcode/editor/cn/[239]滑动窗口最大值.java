//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。 
//
// 返回 滑动窗口中的最大值 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], k = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 1 <= k <= nums.length 
// 
//
// Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1901 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 解题函数的实现（使用 “单调队列” 作为 滑动窗口）
    public int[] maxSlidingWindow(int[] nums, int k) {

        List<Integer> res = new ArrayList<>();

        // “单调队列” 滑动窗口
        MonotonicQueue windows = new MonotonicQueue();

        // 元素入队
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 先填满窗⼝的前 k-1
                windows.push(nums[i]);
            } else {
                // 窗口滑动，加入新数字
                windows.push(nums[i]);
                // 记录最大值
                res.add(windows.getMax());
                // 移出旧数字
                windows.pop(nums[i-(k-1)]);
            }
        }

        // 结果 ArrayList 转 int[]
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }

        return arr;

    }


    // 数据结构“单调队列”的实现（本质是 双链表）
    class MonotonicQueue {
        // 双向链表：可以快速定位和操作 第一个元素 和 最后一个元素
        //  getFirst() getLast() pollLast() addLast()
        LinkedList<Integer> maxq = new LinkedList<>();

        // push
        public void push(int n) {
            // 将队中 小于n 的元素删去
            // （ 注意：相同大小的数要保留，即 <n 而非 <=n ）
            while (!maxq.isEmpty() && maxq.getLast() < n) {
                maxq.pollLast();
            }

            // 将 n 加入队尾
            maxq.addLast(n);
        }

        // 得到队中的最大元素（即队首元素）
        public int getMax() {
            return maxq.getFirst();
        }

        // 删除指定的元素
        public void pop(int n) {
            // n 可能已经被挤出队列（ 因为不够大 ）
            // n 如果在队中，只可能在队首
            if (n == maxq.getFirst()) {
                maxq.pollFirst();
            }
        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)
