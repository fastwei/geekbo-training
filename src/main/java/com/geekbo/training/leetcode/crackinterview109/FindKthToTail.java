package com.geekbo.training.leetcode.crackinterview109;

public class FindKthToTail {

    /**
     * 找出单向链表中倒数第 k 个节点
     *
     * 解题思路：
     * 使用快慢指针的方法，让快指针先走 k-1 步，然后快慢指针同时向前移动，
     * 当快指针到达链表末尾时，慢指针指向的节点就是倒数第 k 个节点。
     *
     * 算法复杂度：
     * 时间复杂度：O(n)，其中 n 是链表的长度。需要遍历整个链表。
     * 空间复杂度：O(1)，只需要额外的两个指针。
     *
     * @param head 链表头节点
     * @param k    倒数第 k 个节点
     * @return 倒数第 k 个节点的值
     */
    public int findKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            return -1;
        }

        ListNode fast = head;
        ListNode slow = head;

        // 快指针先走 k-1 步
        for (int i = 0; i < k - 1; i++) {
            if (fast.next != null) {
                fast = fast.next;
            } else {
                // 如果链表长度小于 k，返回 -1
                return -1;
            }
        }

        // 快慢指针同时向前移动，直到快指针到达链表末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 返回倒数第 k 个节点的值
        return slow.val;
    }

    public static void main(String[] args) {
        FindKthToTail solution = new FindKthToTail();

        // 测试用例1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        int k1 = 2;
        int result1 = solution.findKthToTail(head1, k1);
        System.out.println("测试用例1：");
        System.out.println("预期输出：4");
        System.out.println("实际输出：" + result1);

        // 测试用例2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        int k2 = 5;
        int result2 = solution.findKthToTail(head2, k2);
        System.out.println("测试用例2：");
        System.out.println("预期输出：1");
        System.out.println("实际输出：" + result2);
    }
}