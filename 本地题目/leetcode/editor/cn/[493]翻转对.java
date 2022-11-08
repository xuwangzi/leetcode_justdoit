//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。 
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
//
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 383 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录「翻转对」的个数
    private int count = 0;

    // ⽤于辅助合并有序数组（避免在递归中频繁分配和释放内存）
    private int[] tmp;

    /* main */
    public int reversePairs(int[] nums) {
        sort(nums);
        return count;
    }

    /* merge sort*/
    private void sort(int[] nums) {
        tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(int[] nums, int lo, int hi) {
        // base case
        if (lo == hi) return;

        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        // 进⾏效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 ⼩于 nums[i]
        // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是⼀个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            while (end <= hi && (long) tmp[i] > 2 * (long) tmp[end]) {
                end++;
            }

            count += end - (mid + 1);
        }

        // 数组双指针技巧，合并两个有序数组
        int p1 = lo, p2 = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (p1 == mid + 1) {
                nums[p] = tmp[p2++];
            }else if (p2 == hi+1){
                nums[p]=tmp[p1++];
            } else if (tmp[p1]>tmp[p2]) {
                nums[p] = tmp[p2++];
            }else {
                nums[p] = tmp[p1++];
            }
        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)
