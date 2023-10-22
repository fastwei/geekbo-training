package com.geekbo.training.leetcode.top150;

import java.util.LinkedList;
import java.util.Queue;

class Node3 {
    int val;
    Node3 left;
    Node3 right;
    Node3 next;

    Node3(int val) {
        this.val = val;
    }
}


/**
 * 117. Populating Next Right Pointers in Each Node II
 * <p>
 * Given a binary tree
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
 * Input: root = [1,2,3,4,5,null,7]
 * Output: [1,#,2,3,#,4,5,7,#]
 * Explanation: Given the above binary tree (Figure A),
 * your function should populate each next pointer to point to its next right node,
 * just like in Figure B. The serialized output is in level order as connected by the next pointers,
 * with '#' signifying the end of each level.
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 */
public class PopulatingNextRightPointersInEachNodeII {

    /**
     * 解题思路： 这个问题可以通过迭代的方式来解决。
     * 我们使用两个指针prev和curr，其中prev表示当前层的前一个节点，curr表示当前层的当前节点。
     * 我们首先初始化dummy节点作为prev的初始值，然后从根节点开始遍历二叉树的每一层。
     * 在遍历的过程中，我们根据节点的左右子节点来连接它们的next指针，并更新prev和curr的值。
     * 当遍历完当前层的节点后，我们将prev重新指向dummy节点，并将curr指向dummy的下一个节点，即下一层的第一个节点，然后继续遍历下一层。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：我们需要遍历二叉树的所有节点，因此时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度：我们只使用了常数个额外的节点，因此空间复杂度为O(1)。
     * connect方法连接二叉树的每个next指针，使它指向其右侧的节点。
     * 通过迭代的方式遍历二叉树的每一层，按照题目要求更新每个节点的next指针。最后返回根节点。
     *
     * @param root
     * @return
     */
    public static Node3 connect(Node3 root) {
        if (root == null) {
            return null;
        }

        Node3 dummy = new Node3(0);
        Node3 prev = dummy;
        Node3 curr = root;

        while (curr != null) {
            if (curr.left != null) {
                prev.next = curr.left;
                prev = prev.next;
            }

            if (curr.right != null) {
                prev.next = curr.right;
                prev = prev.next;
            }

            curr = curr.next;

            if (curr == null) {
                prev = dummy;
                curr = dummy.next;
                dummy.next = null;
            }
        }

        return root;
    }


    /**
     * 使用BFS（广度优先搜索）来遍历二叉树的每一层，并根据题目要求更新每个节点的next指针。
     * 我们使用一个队列来保存需要遍历的节点。首先将根节点入队，然后进入循环，直到队列为空。
     * 在每一次循环中，我们先获取当前层的节点数量，然后遍历这些节点。
     * 在遍历的过程中，我们将当前节点的左右子节点入队，并根据题目要求更新节点的next指针。最后返回根节点。
     * <p>
     * 方法的时间复杂度为O(n)，其中n是二叉树的节点数量。
     * 空间复杂度为O(m)，其中m是二叉树每一层的最大节点数量。
     *
     * @param root
     * @return
     */
    public static Node3 connectBFS(Node3 root) {
        if (root == null) {
            return null;
        }

        Queue<Node3> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            Node3 prev = null;

            for (int i = 0; i < size; i++) {
                Node3 curr = queue.poll();

                if (prev != null) {
                    prev.next = curr;
                }

                prev = curr;

                if (curr.left != null) {
                    queue.offer(curr.left);
                }

                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        // 构建测试用例
        Node3 root = new Node3(1);
        root.left = new Node3(2);
        root.right = new Node3(3);
        root.left.left = new Node3(4);
        root.left.right = new Node3(5);
        root.right.right = new Node3(7);

        // 调用方法
        Node3 result = connect(root);

        // 打印结果
        printTree(result);
    }

    private static void printTree(Node3 root) {
        if (root == null) {
            System.out.println("#");
            return;
        }

        System.out.print(root.val + " -> ");
        printTree(root.next);
        printTree(root.left);
        printTree(root.right);
    }
}
