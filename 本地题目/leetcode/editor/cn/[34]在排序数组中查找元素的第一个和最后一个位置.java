//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 1923 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftBound = binarySearch_leftBound(nums,target);
        int rightBound = binarySearch_rightBound(nums,target);

        return new int[]{leftBound,rightBound};

    }


    /**
     * 以下是 3种二分查找 （统一使用闭区间[left, right]）
     */

    // 第一种 最基本的二分查找：
    int binarySearch_normal(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        return -1;
    }

    // 第二种 寻找左侧边界的二分查找：
    int binarySearch_leftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {// 循环结束时，left == right+1
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {// 此情况需要注意
                right = mid - 1;// 。。。
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 最后需要判断 target 是否在 nums 中
        //  1. left 越界
        if (left == nums.length) return -1;// 。。。
        //  2. nums[left] != target
        return nums[left] == target ? left : -1;// 。。。
    }

    // 第三种 寻找右侧边界的二分查找：
    int binarySearch_rightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {// 循环结束时，left == right+1
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                left = mid + 1;// 。。。
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }

        // 最后需要判断 target 是否在 nums 中
        //  1. right 越界
        if (right < 0) return -1;// 。。。
        //  2. nums[right] != target
        return nums[right] == target ? right : -1;// 。。。
    }

}
//leetcode submit region end(Prohibit modification and deletion)
