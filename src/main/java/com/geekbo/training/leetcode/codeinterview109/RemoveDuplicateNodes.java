package com.geekbo.training.leetcode.codeinterview109;

import java.util.HashSet;

class RemoveDuplicateNodes {
    /**
     *
     * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
     *
     * 解题思路： 遍历链表的过程中，使用HashSet来存储已经出现过的节点的值。
     * 如果当前节点的值在HashSet中已经存在，则说明是重复节点，将当前节点从链表中移除。
     * 否则，将当前节点的值加入HashSet中。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：O(N)，其中N是链表的长度，需要遍历整个链表。
     * 空间复杂度：O(N)，需要使用一个HashSet来存储节点的值。
     * 进阶： 如果不得使用临时缓冲区，可以使用两重循环来判断链表中是否有重复节点。
     * 外层循环从链表头开始，内层循环遍历链表头之后的节点，如果发现有重复节点，则将该节点从链表中移除。
     *
     * @param head
     * @return
     */
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        // 使用一个HashSet来存储出现过的节点的值
        HashSet<Integer> set = new HashSet<>();
        ListNode prev = head;
        ListNode curr = head.next;
        set.add(head.val);

        while (curr != null) {
            if (set.contains(curr.val)) {
                // 如果set中已经包含了该节点的值，说明是重复节点，将当前节点从链表中移除
                prev.next = curr.next;
            } else {
                // 如果set中不包含该节点的值，则将该节点的值加入set中
                set.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicateNodes solution = new RemoveDuplicateNodes();

        // 测试用例1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(3);
        head1.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next = new ListNode(1);
        ListNode result1 = solution.removeDuplicateNodes(head1);
        System.out.println("测试用例1：");
        printLinkedList(result1);

        // 测试用例2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(1);
        head2.next.next = new ListNode(1);
        head2.next.next.next = new ListNode(1);
        head2.next.next.next.next = new ListNode(2);
        ListNode result2 = solution.removeDuplicateNodes(head2);
        System.out.println("测试用例2：");
        printLinkedList(result2);
    }

    private static void printLinkedList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
