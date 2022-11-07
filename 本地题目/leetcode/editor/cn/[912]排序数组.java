//给你一个整数数组 nums，请你将该数组升序排列。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,3,1]
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,1,1,2,0,0]
//输出：[0,0,1,1,2,5]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 5 * 10⁴ 
// -5 * 10⁴ <= nums[i] <= 5 * 10⁴ 
// 
//
// Related Topics 数组 分治 桶排序 计数排序 基数排序 排序 堆（优先队列） 归并排序 👍 720 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /* 归并排序（后序遍历二叉树）：复杂度 O(NlogN) */


    /* main */
    public int[] sortArray(int[] nums) {
        // 归并排序对数组进⾏原地排序
        mergeSort(nums);
        return nums;
    }


    /* Merge Sort */
    // ⽤于辅助合并有序数组（避免在递归中频繁分配和释放内存）
    private int[] tmp;

    private void mergeSort(int[] nums) {
        // 先给辅助数组开辟内存空间
        tmp = new int[nums.length];// tmp.length = nums.length
        // 排序整个数组（原地修改）
        mergeSort(nums, 0, nums.length - 1);// 重载 mergeSort()
    }

    // 定义：将⼦数组 nums[lo..hi] 进⾏排序
    private void mergeSort(int[] nums, int lo, int hi) {
        // base case
        if (hi == lo) return;

        // 这样写是为了防⽌溢出，效果等同于 (hi + lo) / 2
        int mid = lo + (hi - lo) / 2;
        // 先对左半部分数组 nums[lo..mid] 排序
        mergeSort(nums, lo, mid);
        // 再对右半部分数组 nums[mid+1..hi] 排序
        mergeSort(nums, mid + 1, hi);
        // 将两部分有序数组合并成⼀个有序数组
        mergeSort(nums, lo, mid, hi);// 重载 mergeSort()
    }

    // 并成 nums[lo..mid] 和 nums[mid+1..hi]
    private void mergeSort(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 拷贝到辅助数组中
        for (int i = lo; i <= hi; i++) {
            tmp[i] = nums[i];
        }

        // 数组双指针技巧，合并两个有序数组
        int ptr_tmp_1 = lo, ptr_tmp_2 = mid + 1;
        for (int ptr_nums = lo; ptr_nums <= hi; ptr_nums++) {
            if (ptr_tmp_1 == mid + 1) {
                // 左半边数组已全部被合并
                nums[ptr_nums] = tmp[ptr_tmp_2];
                ptr_tmp_2++;
            } else if (ptr_tmp_2 == hi + 1) {
                // 右半边数组已全部被合并
                nums[ptr_nums] = tmp[ptr_tmp_1];
                ptr_tmp_1++;
            }else if (tmp[ptr_tmp_1]>tmp[ptr_tmp_2]){
                nums[ptr_nums]=tmp[ptr_tmp_2];
                ptr_tmp_2++;
            } else {
                nums[ptr_nums]=tmp[ptr_tmp_1];
                ptr_tmp_1++;
            }

        }

    }


}
//leetcode submit region end(Prohibit modification and deletion)
