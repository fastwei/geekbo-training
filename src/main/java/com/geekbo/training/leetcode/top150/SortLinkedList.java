package com.geekbo.training.leetcode.top150;


import com.geekbo.training.leetcode.base.ListNode;

/**
 * Divide & Conquer
 *
 *Given the head of a linked list, return the list after sorting it in ascending order.
 * example:
 * Input: head = [4,2,1,3]
 * Output: [1,2,3,4]
 * Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
 *
 *
 *解题思路：
 *
 * 使用归并排序对链表进行排序。
 * 找到链表的中间节点，然后将链表分成两部分。
 * 递归地对左半部分和右半部分进行排序。
 * 合并两个有序链表，得到最终的有序链表。
 * 复杂度分析：
 *
 * 时间复杂度：O(n log n)，归并排序的时间复杂度。
 * 空间复杂度：O(log n)，递归栈的深度。
 *
 * 类型：Divide & Conquer
 */
public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // 如果链表为空或只有一个节点，无需排序
        }
        
        // 使用归并排序对链表进行排序
        ListNode middle = findMiddle(head);
        ListNode right = middle.next;
        middle.next = null; // 切断链表为两部分
        
        ListNode leftSorted = sortList(head);
        ListNode rightSorted = sortList(right);
        
        return merge(leftSorted, rightSorted);
    }
    
    // 找到链表的中间节点，用于拆分链表
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }

    /**
     *
     *该方法用于合并两个已排序的链表。它接受两个参数，left 和 right，它们代表两个链表的头节点。
     *
     * 该方法创建一个名为 dummy 的新链表和一个 current 指针，用于跟踪合并后链表中的当前节点。
     *
     * 接着，它遍历两个输入链表，比较当前位置节点的值，并将较小的值添加到合并后的链表中。然后，将 current 指针前进到合并后链表的下一个节点。
     *
     * 遍历结束后，如果输入链表中仍然存在未处理的节点，它们将被追加到合并后链表的末尾。
     *
     * 最后，该方法返回合并后链表的头节点，也就是 dummy 节点之后的下一个节点。
     *
     *
     * @param left
     * @param right
     * @return
     */
    // 合并两个有序链表
    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        
        while (left != null && right != null) {
            if (left.val < right.val) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }
        
        if (left != null) {
            current.next = left;
        } else {
            current.next = right;
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建示例输入的链表
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);

        // 调用排序方法
        SortLinkedListGood solution = new SortLinkedListGood();
        ListNode sortedHead = solution.sortList(head);

        // 打印排序后的链表
        while (sortedHead != null) {
            System.out.print(sortedHead.val + " ");
            sortedHead = sortedHead.next;
        }
    }
}
