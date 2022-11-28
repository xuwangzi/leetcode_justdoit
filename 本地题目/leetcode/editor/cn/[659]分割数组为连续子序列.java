//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 示例 2： 
//
// 
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 示例 3： 
//
// 
//输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10000 
// 
//
// Related Topics 贪心 数组 哈希表 堆（优先队列） 👍 410 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/*
 * 对于数组中的元素x，如果存在一个子序列以x-1结尾，则可以将x加入该子序列中。
 * 将x加入已有的子序列总是比新建一个只包含x的子序列更优，因为前者可以将一个已有的子序列的长度增加1，而后者新建一个长度为1的子序列，
 * 而题目要求分割成的子序列的长度都不小于3，因此应该尽量避免新建短的子序列。
 */
class Solution {
    public boolean isPossible(int[] nums) {
        /* countMap：key表示数字num，value表示数字num出现的次数count */
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        /* endMap：key表示存在一个子序列以key-1结尾，可以将key加入该子序列中，value表示这种子序列的个数 */
        Map<Integer, Integer> endMap = new HashMap<Integer, Integer>();

        // 统计 nums 中元素的频率
        for (int i = 0; i < nums.length; i++) {
            countMap.put(nums[i], countMap.getOrDefault(nums[i], 0) + 1);
        }

        // 开始配对顺子
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                int times = endMap.getOrDefault(num, 0);

                if (times > 0) {
                    /* 对于数字num，优先考虑将其加入到子序列以num-1结尾的子数组中 */
                    countMap.put(num, count - 1);
                    endMap.put(num, endMap.get(num) - 1);
                    endMap.put(num + 1, endMap.getOrDefault(num + 1, 0) + 1);
                } else {
                    /* 以当前数字num开始一个新的数组，num+1和num+2作为第二第三个值 */
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.put(num + 3, endMap.getOrDefault(num + 3, 0) + 1);
                    } else {
                        // 无法配出顺子
                        return false;
                    }
                }
            }
        }

        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
