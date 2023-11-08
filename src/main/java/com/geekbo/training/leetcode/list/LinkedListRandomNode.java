package com.geekbo.training.leetcode.list;

import com.geekbo.training.leetcode.base.ListNode;

import java.util.Random;

/**
 * 382. Linked List Random Node
 * Medium
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same probability of being chosen.
 * <p>
 * Implement the Solution class:
 * <p>
 * Solution(ListNode head) Initializes the object with the head of the singly-linked list head.
 * int getRandom() Chooses a node randomly from the list and returns its value. All the nodes of the list should be equally likely to be chosen.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input
 * ["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 3, 2, 2, 3]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 2, 3]);
 * solution.getRandom(); // return 1
 * solution.getRandom(); // return 3
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 2
 * solution.getRandom(); // return 3
 * // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the linked list will be in the range [1, 104].
 * -104 <= Node.val <= 104
 * At most 104 calls will be made to getRandom.
 * <p>
 * <p>
 * Follow up:
 * <p>
 * What if the linked list is extremely large and its length is unknown to you?
 * Could you solve this efficiently without using extra space?
 */

/**
 *
 * 解题思路：
 *
 * 遍历链表，使用random.nextInt(count)来决定是否选择当前节点的值作为结果。
 * 对于第一个节点，选择它的概率为1，对于第二个节点，选择它的概率为1/2，对于第三个节点，选择它的概率为1/3，以此类推。
 * 算法复杂度分析：
 *
 * 初始化Solution对象的时间复杂度为O(1)。
 * getRandom方法的时间复杂度为O(n)，其中n是链表的长度。需要遍历整个链表来选择随机节点的值。
 * 这个实现可以保证每个节点被选择的概率相等，且不需要使用额外的空间。对于大规模且长度未知的链表，这个实现依然有效。
 */
class LinkedListRandomNode {
    private ListNode head;
    private Random random;

    public LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom() {
        int count = 0;
        int result = 0;
        ListNode curr = head;
        while (curr != null) {
            count++;
            if (random.nextInt(count) == 0) {
                result = curr.val;
            }
            curr = curr.next;
        }
        return result;
    }

    public static void main(String[] args) {
        // 创建一个链表 1->2->3
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        LinkedListRandomNode solution = new LinkedListRandomNode(head);

        // 测试 getRandom 方法
        System.out.println(solution.getRandom()); // 预期输出: 1
        System.out.println(solution.getRandom()); // 预期输出: 2
        System.out.println(solution.getRandom()); // 预期输出: 3
    }
}



