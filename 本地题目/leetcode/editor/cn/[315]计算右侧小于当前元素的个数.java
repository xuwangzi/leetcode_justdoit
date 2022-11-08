//给你一个整数数组 nums ，按要求返回一个新数组 counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于 
//nums[i] 的元素的数量。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,2,6,1]
//输出：[2,1,1,0] 
//解释：
//5 的右侧有 2 个更小的元素 (2 和 1)
//2 的右侧仅有 1 个更小的元素 (1)
//6 的右侧有 1 个更小的元素 (1)
//1 的右侧有 0 个更小的元素
// 
//
// 示例 2： 
//
// 
//输入：nums = [-1]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：nums = [-1,-1]
//输出：[0,0]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 909 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 构造Pair数据类型
    private class Pair {
        int val, id;

        Pair(int val, int id) {
            this.val = val;
            this.id = id;
        }

    }


    private Pair[] tmp;
    private int[] count;

    /* main */
    public List<Integer> countSmaller(int[] nums) {
        // init
        int len = nums.length;
        count = new int[len];
        tmp = new Pair[len];
        Pair[] arr = new Pair[len];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = new Pair(nums[i], i);
        }

        // merge sort
        sort(arr, 0, len - 1);

        // result
        List<Integer> res = new LinkedList<>();
        for (int c : count) res.add(c);
        return res;
    }

    /* merge sort */
    private void sort(Pair[] arr, int lo, int hi) {
        // base case
        if (lo == hi) return;

        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }


    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tmp[i] = arr[i];
        }

        int p1 = lo, p2 = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (p1 == mid + 1) {
                arr[p] = tmp[p2++];
            } else if (p2 == hi + 1) {
                arr[p] = tmp[p1++];
                count[arr[p].id] += p2 - (mid + 1);
            } else if (tmp[p1].val > tmp[p2].val) {
                arr[p] = tmp[p2++];
            } else {
                arr[p] = tmp[p1++];
                count[arr[p].id] += p2 - (mid + 1);
            }
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
