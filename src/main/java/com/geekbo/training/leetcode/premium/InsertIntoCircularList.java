package com.geekbo.training.leetcode.premium;

import com.geekbo.training.leetcode.base.ListNode;

/**
 * 708. 循环有序列表的插入
 * 已解答
 * 中等
 * 
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环非降序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），你需要创建一个循环有序列表并返回这个节点。否则，请返回原先给定的节点。
 * 
 * 示例 1：
 * 输入：head = [3,4,1], insertVal = 2
 * 输出：[3,4,1,2]
 * 解释：在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 * 
 * 示例 2：
 * 输入：head = [], insertVal = 1
 * 输出：[1]
 * 解释：列表为空（给定的节点是 null），创建一个循环有序列表并返回这个节点。
 * 
 * 示例 3：
 * 输入：head = [1], insertVal = 0
 * 输出：[1,0]
 * 
 * 提示：
 * 0 <= Number of Nodes <= 5 * 104
 * -106 <= Node.val, insertVal <= 106
 */
public class InsertIntoCircularList {

//    public Node insert(Node head, int insertVal) {
//        if (head == null) {
//            Node node = new Node(insertVal);
//            node.next = node;
//            return node;
//        }
//
//        Node cur = head;
//        while (true) {
//            if (cur.val <= insertVal && insertVal <= cur.next.val) {
//                break;
//            }
//
//            if (cur.val > cur.next.val && (insertVal >= cur.val || insertVal <= cur.next.val)) {
//                break;
//            }
//
//            cur = cur.next;
//            if (cur == head) {
//                break;
//            }
//        }
//
//        Node newNode = new Node(insertVal);
//        newNode.next = cur.next;
//        cur.next = newNode;
//
//        return head;
//    }

    /**
     *
     * 解题思路：
     *
     * 首先判断给定的列表是否为空，如果为空，则创建一个只包含插入元素的节点，并将其指向自身，然后返回该节点。
     * 如果列表不为空，则从列表的头部开始遍历，找到插入元素的位置。
     * 如果当前节点的值小于等于插入元素的值，并且插入元素的值小于等于下一个节点的值，则找到了插入位置。
     * 如果当前节点的值大于下一个节点的值，并且插入元素的值大于等于当前节点的值或者小于等于下一个节点的值，则找到了插入位置。
     * 在找到插入位置后，创建一个新的节点，并将其插入到当前节点和下一个节点之间。
     * 最后，返回原始的头节点。
     *
     * 算法复杂度分析：
     *
     * 时间复杂度：O(n)，其中 n 是列表的长度。在最坏的情况下，需要遍历整个列表才能找到插入位置。
     * 空间复杂度：O(1)，不需要额外的空间。
     * @param head
     * @param insertVal
     * @return
     */
    public ListNode insert(ListNode head, int insertVal) {
        if (head == null) {
            ListNode node = new ListNode(insertVal);
            node.next = node;
            return node;
        }
        
        ListNode cur = head;
        while (true) {
            if (cur.val <= insertVal && insertVal <= cur.next.val) {
                break;
            }
            
            if (cur.val > cur.next.val && (insertVal >= cur.val || insertVal <= cur.next.val)) {
                break;
            }
            
            cur = cur.next;
            if (cur == head) {
                break;
            }
        }
        
        ListNode newNode = new ListNode(insertVal);
        newNode.next = cur.next;
        cur.next = newNode;
        
        return head;
    }

    public static void main(String[] args) {
        // 创建一个测试用例
        ListNode head = new ListNode(3);
        head.next = new ListNode(4);
        head.next.next = new ListNode(1);
        int insertVal = 2;

        // 创建解决方案的实例
        InsertIntoCircularList solution = new InsertIntoCircularList();

        // 调用插入函数
        ListNode result = solution.insert(head, insertVal);

        // 打印结果
        ListNode cur = result;
        do {
            System.out.print(cur.val + " ");
            cur = cur.next;
        } while (cur != result);
        System.out.println();
    }
}