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
    /*
        两种方法：
            归并排序 是「稳定排序」
            快速排序 是「不稳定排序」
    */


//    /* 方法一 */
//    /* 归并排序（后序遍历二叉树）：复杂度 O(NlogN) */
//
//
//    /* main */
//    public int[] sortArray(int[] nums) {
//        // 归并排序对数组进⾏原地排序
//        mergeSort(nums);
//        return nums;
//    }
//
//
//    /* Merge Sort */
//    // ⽤于辅助合并有序数组（避免在递归中频繁分配和释放内存）
//    private int[] tmp;
//
//    private void mergeSort(int[] nums) {
//        // 先给辅助数组开辟内存空间
//        tmp = new int[nums.length];// tmp.length = nums.length
//        // 排序整个数组（原地修改）
//        mergeSort(nums, 0, nums.length - 1);// 重载 mergeSort()
//    }
//
//    // 定义：将⼦数组 nums[lo..hi] 进⾏排序
//    private void mergeSort(int[] nums, int lo, int hi) {
//        // base case
//        if (hi == lo) return;
//
//        // 这样写是为了防⽌溢出，效果等同于 (hi + lo) / 2
//        int mid = lo + (hi - lo) / 2;
//        // 先对左半部分数组 nums[lo..mid] 排序
//        mergeSort(nums, lo, mid);
//        // 再对右半部分数组 nums[mid+1..hi] 排序
//        mergeSort(nums, mid + 1, hi);
//        // 将两部分有序数组合并成⼀个有序数组
//        mergeSort(nums, lo, mid, hi);// 重载 mergeSort()
//    }
//
//    // 并成 nums[lo..mid] 和 nums[mid+1..hi]
//    private void mergeSort(int[] nums, int lo, int mid, int hi) {
//        // 先把 nums[lo..hi] 拷贝到辅助数组中
//        for (int i = lo; i <= hi; i++) {
//            tmp[i] = nums[i];
//        }
//
//        // 数组双指针技巧，合并两个有序数组
//        int ptr_tmp_1 = lo, ptr_tmp_2 = mid + 1;
//        for (int ptr_nums = lo; ptr_nums <= hi; ptr_nums++) {
//            if (ptr_tmp_1 == mid + 1) {
//                // 左半边数组已全部被合并
//                nums[ptr_nums] = tmp[ptr_tmp_2];
//                ptr_tmp_2++;
//            } else if (ptr_tmp_2 == hi + 1) {
//                // 右半边数组已全部被合并
//                nums[ptr_nums] = tmp[ptr_tmp_1];
//                ptr_tmp_1++;
//            }else if (tmp[ptr_tmp_1]>tmp[ptr_tmp_2]){
//                nums[ptr_nums]=tmp[ptr_tmp_2];
//                ptr_tmp_2++;
//            } else {
//                nums[ptr_nums]=tmp[ptr_tmp_1];
//                ptr_tmp_1++;
//            }
//
//        }
//
//    }




    /* 方法二 */
    /* 快速排序：时间复杂度 O(NlogN)；空间复杂度 O(logN) */


    /* main */
    public int[] sortArray(int[] nums) {
        // 为了避免出现耗时的极端情况，先随机打乱
        shuffle(nums);
        // 排序整个数组（原地修改）
        quickSort(nums, 0, nums.length - 1);

        return nums;
    }


    /* 快速排序 */
    private void quickSort(int[] nums, int lo, int hi) {
        // base case
        if (lo >= hi) return;

        // 对 nums[lo..hi] 进行切分
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p = partition(nums, lo, hi);

        quickSort(nums, lo, p-1);
        quickSort(nums, p+1, hi);
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
