//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    ListNode partition(ListNode head, int x) {
        /**
         * 思路：
         * 把原链 表分成两个⼩链表，
         * ⼀个链表中的元素⼤⼩都⼩于 x，另⼀个链表中的元素都⼤于等于 x，
         * 最后再把这两条 链表接到⼀起。
         */
        // 虚拟头节点
        ListNode dummy1 = new ListNode(-1), p1 = dummy1;
        ListNode dummy2 = new ListNode(-1), p2 = dummy2;

        ListNode p = head;

        while (p != null) {
            // 将原链表的节点放入新链表
            if (p.val < x) {
                p1.next = p;
                p1 = p1.next;
            } else {
                p2.next = p;
                p2 = p2.next;
            }

            // 断开原链表节点的next指针
            ListNode tmp = p.next;
            p.next=null;
            p=tmp;
        }

        // 连接两个链表
        p1.next = dummy2.next;

        return dummy1.next;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
