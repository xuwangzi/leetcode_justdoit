//给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数
//目来描述。 
//
// 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [2,7,11,15], nums2 = [1,10,4,11]
//输出：[2,11,7,15]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [12,24,8,32], nums2 = [13,25,32,11]
//输出：[24,32,8,12]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length <= 10⁵ 
// nums2.length == nums1.length 
// 0 <= nums1[i], nums2[i] <= 10⁹ 
// 
//
// Related Topics 贪心 数组 双指针 排序 👍 203 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int len = nums1.length;

        // nums2 降序
        // 为了保留nums2的元素顺序，使用 优先级序列
        //  i. 构造空的优先级序列
        PriorityQueue<int[]> maxpq = new PriorityQueue<>(
                (int[] pair1, int[] pair2)->{
                    return pair2[1]-pair1[1];// 按照键值对的值进行排序
                }
        );
        //  ii.将nums2的元素注入优先级序列中
        for (int i = 0; i < len; i++) {
            maxpq.offer(new int[]{i,nums2[i]});
        }

        // nums1 升序（直接重新排序）
        Arrays.sort(nums1);

        // 左右指针
        int left = 0,right=len-1;
        int[] res = new int[len];

        // 田忌赛马 策略
        while(!maxpq.isEmpty()){
            int[] pair = maxpq.poll();// pair[1] 是nums2中的最大值，pair[0] 是对于的索引

            if (pair[1]<nums1[right]){
                res[pair[0]]=nums1[right];
                right--;
            }else {
                res[pair[0]]=nums1[left];
                left++;
            }
        }

        return res;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
