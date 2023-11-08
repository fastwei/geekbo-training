package com.geekbo.training.leetcode.top150;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * Given the heads of two singly linked-lists headA and headB,
 * return the node at which the two lists intersect.
 * If the two linked lists have no intersection at all, return null.
 * <p>
 * For example, the following two linked lists begin to intersect at node c1:
 * <p>
 * <p>
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * <p>
 * Note that the linked lists must retain their original structure after the function returns.
 * <p>
 * Custom Judge:
 * <p>
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * <p>
 * intersectVal - The value of the node where the intersection occurs.
 * This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program.
 * If you correctly return the intersected node, then your solution will be accepted.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * Output: Intersected at '8'
 * Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,6,1,8,4,5].
 * There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 * - Note that the intersected node's value is not 1
 * because the nodes with value 1 in A and B (2nd node in A and 3rd node in B) are different node references.
 * In other words, they point to two different locations in memory,
 * while the nodes with value 8 in A and B (3rd node in A and 4th node in B) point to the same location in memory.
 * Example 2:
 * <p>
 * <p>
 * Input: intersectVal = 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * Output: Intersected at '2'
 * Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect).
 * From the head of A, it reads as [1,9,1,2,4]. From the head of B, it reads as [3,2,4]. T
 * here are 3 nodes before the intersected node in A;
 * There are 1 node before the intersected node in B.
 * Example 3:
 * <p>
 * <p>
 * Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * Output: No intersection
 * Explanation: From the head of A, it reads as [2,6,4].
 * From the head of B, it reads as [1,5].
 * Since the two lists do not intersect, intersectVal must be 0,
 * while skipA and skipB can be arbitrary values.
 * Explanation: The two lists do not intersect, so return null.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes of listA is in the m.
 * The number of nodes of listB is in the n.
 * 1 <= m, n <= 3 * 104
 * 1 <= Node.val <= 105
 * 0 <= skipA < m
 * 0 <= skipB < n
 * intersectVal is 0 if listA and listB do not intersect.
 * intersectVal == listA[skipA] == listB[skipB] if listA and listB intersect.
 * <p>
 * <p>
 * Follow up: Could you write a solution that runs in O(m + n) time and use only O(1) memory?
 */
public class IntersectionOfTwoLinkedLists {
    /**
     * 解题思路：
     * 使用双指针的方法。
     * 定义两个指针pA和pB分别指向两个链表的头节点headA和headB。
     * 同时遍历两个链表，当pA遍历到链表末尾时，将其指向headB，继续遍历；当pB遍历到链表末尾时，将其指向headA，继续遍历。
     * 如果两个链表相交，pA和pB一定会在相交节点处相遇；
     * 如果两个链表不相交，pA和pB会同时到达链表末尾，此时它们的值都为null，返回null即可。
     * <p>
     * 算法的时间复杂度为O(m+n)，其中m和n分别是两个链表的长度。
     * 算法的空间复杂度为O(1)。
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }

    public static void main(String[] args) {
        // Test case 1
        ListNode intersectNode = new ListNode(8);
        ListNode headA1 = new ListNode(4);
        headA1.next = new ListNode(1);
        headA1.next.next = intersectNode;
        ListNode headB1 = new ListNode(5);
        headB1.next = new ListNode(6);
        headB1.next.next = new ListNode(1);
        headB1.next.next.next = intersectNode;
        // 预期输出为8
        System.out.println(getIntersectionNode(headA1, headB1).val);

        // Test case 2
        ListNode intersectNode2 = new ListNode(2);
        ListNode headA2 = new ListNode(1);
        headA2.next = new ListNode(9);
        headA2.next.next = new ListNode(1);
        headA2.next.next.next = intersectNode2;
        ListNode headB2 = new ListNode(3);
        headB2.next = intersectNode2;
        // 预期输出为2
        System.out.println(getIntersectionNode(headA2, headB2).val);

        // Test case 3
        ListNode headA3 = new ListNode(2);
        headA3.next = new ListNode(6);
        headA3.next.next = new ListNode(4);
        ListNode headB3 = new ListNode(1);
        headB3.next = new ListNode(5);
        // 预期输出为null
        System.out.println(getIntersectionNode(headA3, headB3));
    }
}