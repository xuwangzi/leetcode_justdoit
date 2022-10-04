//给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。 
//
// 
//
// 示例 1： 
// 
// 
//输入：head = [1,2,3,4,5], n = 2
//输出：[1,2,3,5]
// 
//
// 示例 2： 
//
// 
//输入：head = [1], n = 1
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：head = [1,2], n = 1
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 链表中结点的数目为 sz 
// 1 <= sz <= 30 
// 0 <= Node.val <= 100 
// 1 <= n <= sz 
// 
//
// 
//
// 进阶：你能尝试使用一趟扫描实现吗？ 
//
// Related Topics 链表 双指针 👍 2225 👎 0


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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 先找到倒数第n+1个节点
        ListNode x = findFromEnd(dummy, n+1);
        // 再删除倒数第n个节点
        x.next = x.next.next;

        return dummy.next;
    }


    // 找到倒数第k个节点（使用快慢指针）
    private ListNode findFromEnd(ListNode head, int k){
        // 快指针
        ListNode p_fast = head;
        for (int i = 0; i < k; i++) {
            p_fast = p_fast.next;
        }
        // 慢指针
        ListNode p_slow = head;

        // 快慢指针同时移动
        while (p_fast!=null){
            p_fast = p_fast.next;
            p_slow = p_slow.next;
        }

        // 返回慢指针
        return p_slow;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
