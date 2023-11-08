package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 725. Split Linked List in Parts
 * Medium
 * Given the head of a singly linked list and an integer k,
 * split the linked list into k consecutive linked list parts.
 * <p>
 * The length of each part should be as equal as possible:
 * no two parts should have a size differing by more than one.
 * This may lead to some parts being null.
 * <p>
 * The parts should be in the order of occurrence in the input list,
 * and parts occurring earlier should always have a size greater than or equal to parts occurring later.
 * <p>
 * Return an array of the k parts.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3], k = 5
 * Output: [[1],[2],[3],[],[]]
 * Explanation:
 * The first element output[0] has output[0].val = 1, output[0].next = null.
 * The last element output[4] is null, but its string representation as a ListNode is [].
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
 * Output: [[1,2,3,4],[5,6,7],[8,9,10]]
 * Explanation:
 * The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 */
public class SplitLinkedListInParts {
    /**
     * The splitListToParts method takes the head of a linked list and an integer k as input.
     * It splits the linked list into k consecutive linked list parts,
     * with the length of each part as equal as possible. The result is an array of the k parts.
     * <p>
     * The algorithm consists of three steps:
     * <p>
     * Calculate the length of the linked list.
     * Calculate the size of each part and the number of larger parts.
     * Split the linked list into parts by iterating through the list and cutting off each part from the rest of the list.
     * The time complexity of this algorithm is O(n), where n is the length of the linked list.
     * The space complexity is O(k), as we need to store the result array.
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode head, int k) {
        // Step 1: Calculate the length of the linked list
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Step 2: Calculate the size of each part and the number of larger parts
        int partSize = length / k;
        int extra = length % k;

        // Step 3: Split the linked list into parts
        ListNode[] result = new ListNode[k];
        curr = head;
        for (int i = 0; i < k && curr != null; i++) {
            result[i] = curr;
            int currentPartSize = partSize + (extra > 0 ? 1 : 0);
            extra--;

            // Move to the next part
            for (int j = 0; j < currentPartSize - 1; j++) {
                curr = curr.next;
            }

            // Cut off the current part from the rest of the linked list
            ListNode next = curr.next;
            curr.next = null;
            curr = next;
        }

        return result;
    }

    // Test cases
    public static void main(String[] args) {
        SplitLinkedListInParts solution = new SplitLinkedListInParts();

        // Test case 1
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        int k1 = 5;
        ListNode[] result1 = solution.splitListToParts(head1, k1);
        for (ListNode node : result1) {
            printList(node);
        }

        // Test case 2
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(2);
        head2.next.next = new ListNode(3);
        head2.next.next.next = new ListNode(4);
        head2.next.next.next.next = new ListNode(5);
        head2.next.next.next.next.next = new ListNode(6);
        head2.next.next.next.next.next.next = new ListNode(7);
        head2.next.next.next.next.next.next.next = new ListNode(8);
        head2.next.next.next.next.next.next.next.next = new ListNode(9);
        head2.next.next.next.next.next.next.next.next.next = new ListNode(10);
        int k2 = 3;
        ListNode[] result2 = solution.splitListToParts(head2, k2);
        for (ListNode node : result2) {
            printList(node);
        }
    }

    // Helper method to print a linked list
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
