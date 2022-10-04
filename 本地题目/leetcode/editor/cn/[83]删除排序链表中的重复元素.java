//给定一个已排序的链表的头
// head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,1,2]
//输出：[1,2]
// 
//
// 示例 2： 
// 
// 
//输入：head = [1,1,2,3,3]
//输出：[1,2,3]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点数目在范围 [0, 300] 内 
// -100 <= Node.val <= 100 
// 题目数据保证链表已经按升序 排列 
// 
//
// Related Topics 链表 👍 858 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 特例：为空
        if (head == null) {
            return null;
        }

        // 快慢指针
        ListNode p_slow = head, p_fast = head;
        while (p_fast!=null) {
            if (p_fast.val != p_slow.val) {
                p_slow.next = p_fast;
                p_slow=p_slow.next;
            }

            p_fast=p_fast.next;

        }

        // 断开与后面节点的连接
        p_slow.next = null;
        return head;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
