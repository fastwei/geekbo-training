package com.geekbo.training.leetcode.codeinterview109;

/**
 * 题目：回文链表
 * 难度：简单
 * <p>
 * 问题描述：
 * 给定一个链表，判断链表是否为回文链表。
 * <p>
 * 示例：
 * 输入：1->2->2->1
 * 输出：true
 * <p>
 * 进阶要求：
 * 你能否用O(n)时间复杂度和O(1)空间复杂度解决此题？
 * <p>
 * 解题思路：
 * 1. 使用快慢指针找到链表的中点。
 * 2. 将链表的后半部分反转。
 * 3. 比较链表的前半部分和反转后的后半部分是否相等。
 * <p>
 * 算法复杂度分析：
 * - 时间复杂度：O(n)，其中n是链表的长度。
 * - 空间复杂度：O(1)。
 */
class PalindromeLinkedListChecker {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 使用快慢指针找到链表的中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 反转链表的后半部分
        ListNode secondHalf = reverseList(slow.next);

        // 比较链表的前半部分和反转后的后半部分是否相等
        ListNode p1 = head;
        ListNode p2 = secondHalf;
        while (p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    // 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        // 创建测试用例
        ListNode test1 = new ListNode(1);
        test1.next = new ListNode(2);

        ListNode test2 = new ListNode(1);
        test2.next = new ListNode(2);
        test2.next.next = new ListNode(2);
        test2.next.next.next = new ListNode(1);

        // 创建解决方案对象
        PalindromeLinkedListChecker solution = new PalindromeLinkedListChecker();

        // 测试用例1的预期结果是false
        boolean result1 = solution.isPalindrome(test1);
        System.out.println(result1);

        // 测试用例2的预期结果是true
        boolean result2 = solution.isPalindrome(test2);
        System.out.println(result2);
    }
}