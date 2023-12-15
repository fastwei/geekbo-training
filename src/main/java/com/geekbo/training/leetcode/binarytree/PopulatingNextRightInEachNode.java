package com.geekbo.training.leetcode.binarytree;

/**
 * 116. Populating Next Right Pointers in Each Node
 * Medium
 * You are given a perfect binary tree where all leaves are on the same level,
 * and every parent has two children. The binary tree has the following definition:
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * Populate each next pointer to point to its next right node.
 * If there is no next right node, the next pointer should be set to NULL.
 * <p>
 * Initially, all next pointers are set to NULL.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [1,#,2,3,#,4,5,6,7,#]
 * Explanation: Given the above perfect binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 */
class PopulatingNextRightInEachNode {
    /**
     * 这个问题的解题思路是使用层序遍历的方式，通过设置 next 指针连接每个节点的右侧节点。
     * <p>
     * 算法的时间复杂度是O(N)，其中N是二叉树中的节点数。空间复杂度是O(1)，因为我们只使用了固定的额外空间。
     * <p>
     * 请确保你已经导入了 Node 类，并且每个节点的 next 指针在连接之前已经初始化为 null。
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;

            while (head != null) {
                // 将当前节点的左子节点的next指向右子节点
                head.left.next = head.right;

                // 如果当前节点的next不为空，则将当前节点的右子节点的next指向当前节点的next节点的左子节点
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                head = head.next;
            }

            leftmost = leftmost.left;
        }

        return root;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node next;

    Node(int val) {
        this.val = val;
    }
}
