package com.geekbo.training.leetcode.codeinterview109;

/**
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * <p>
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * <p>
 * 编写函数对这两个整数求和，并用链表形式返回结果。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：(7 -> 1 -> 6) + (5 -> 9 -> 2)，即617 + 295
 * 输出：2 -> 1 -> 9，即912
 * 进阶：思考一下，假设这些数位是正向存放的，又该如何解决呢?
 * <p>
 * 示例：
 * <p>
 * 输入：(6 -> 1 -> 7) + (2 -> 9 -> 5)，即617 + 295
 * 输出：9 -> 1 -> 2，即912
 */
class LinkedListSum {
    /**
     * 解题思路：
     * <p>
     * 遍历两个链表，相同位置的数位相加，并将进位加到下一位的计算中。
     * 利用一个哑节点作为结果链表的头节点，并使用一个指针指向当前位置。
     * 遍历结束后，如果最后还有进位，则在结果链表末尾添加一个节点。
     * 返回结果链表的头节点。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(max(m,n))，其中 m 和 n 分别为两个链表的长度，需要遍历两个链表的所有节点。
     * 空间复杂度：O(max(m,n))，需要创建一个新的链表来存储结果。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建一个哑节点作为结果链表的头节点
        ListNode dummy = new ListNode(0);
        // 遍历两个链表的指针和进位标志
        ListNode p = l1, q = l2, curr = dummy;
        int carry = 0;
        // 遍历两个链表，相同位置的数位相加，并将进位加到下一位的计算中
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        // 如果最后还有进位，则在结果链表末尾添加一个节点
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        // 返回结果链表的头节点
        return dummy.next;
    }

    public static void main(String[] args) {
        // 创建测试用例链表
        ListNode l1 = new ListNode(7);
        l1.next = new ListNode(1);
        l1.next.next = new ListNode(6);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(2);

        // 调用方法求解链表求和
        LinkedListSum solution = new LinkedListSum();
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 打印结果链表
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
