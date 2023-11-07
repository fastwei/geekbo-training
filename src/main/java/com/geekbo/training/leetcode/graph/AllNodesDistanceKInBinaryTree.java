package com.geekbo.training.leetcode.graph;


import com.geekbo.training.leetcode.base.TreeNode;

import java.util.*;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Medium
 * Topics
 * Companies
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 * <p>
 * You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 * Example 2:
 * <p>
 * Input: root = [1], target = 1, k = 3
 * Output: []
 */
public class AllNodesDistanceKInBinaryTree {
    private Map<TreeNode, TreeNode> parentMap;

    /**
     * 给定一个二叉树的根节点root，目标节点target和一个整数k，返回距离目标节点距离为k的所有节点的值。
     * <p>
     * 解题思路：
     * <p>
     * 这个问题可以使用深度优先搜索（DFS）来解决。我们可以先使用DFS遍历二叉树，构建一个从每个节点到其父节点的映射关系。
     * 然后，我们使用BFS来遍历距离目标节点距离为k的所有节点。在BFS的过程中，我们可以使用一个队列来保存当前层的节点，
     * 并使用一个集合来记录已经访问过的节点，以避免重复访问。当我们遍历到距离目标节点距离为k的节点时，将其值添加到结果集中。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n)，其中n是二叉树中的节点数。
     * 空间复杂度：O(n)，我们使用了一个队列和一个集合来进行BFS，并且需要一个HashMap来记录每个节点的父节点。
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        parentMap = new HashMap<>();
        buildParentMap(root, null);

        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (dist == k) {
                for (int i = 0; i < size; i++) {
                    result.add(queue.poll().val);
                }
                return result;
            }

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                if (curr.left != null && !visited.contains(curr.left)) {
                    queue.offer(curr.left);
                    visited.add(curr.left);
                }
                if (curr.right != null && !visited.contains(curr.right)) {
                    queue.offer(curr.right);
                    visited.add(curr.right);
                }
                TreeNode parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    queue.offer(parent);
                    visited.add(parent);
                }
            }

            dist++;
        }

        return result;
    }

    private void buildParentMap(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }

        parentMap.put(node, parent);
        buildParentMap(node.left, node);
        buildParentMap(node.right, node);
    }

    public static void main(String[] args) {
        AllNodesDistanceKInBinaryTree solution = new AllNodesDistanceKInBinaryTree();

        // 测试用例1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);
        int target1 = 5;
        int k1 = 2;
        List<Integer> expected1 = Arrays.asList(7, 4, 1);
        List result1 = solution.distanceK(root1, new TreeNode(target1), k1);
        System.out.println("测试用例1:");
        System.out.println("预期输出: " + expected1);
        System.out.println("实际输出: " + result1);


        // 测试用例2
        TreeNode root2 = new TreeNode(1);
        int target2 = 1;
        int k2 = 3;
        List<Integer> expected2 = new ArrayList<>();
        List<Integer> result2 = solution.distanceK(root2, new TreeNode(target2), k2);
        System.out.println("测试用例2:");
        System.out.println("预期输出: " + expected2);
        System.out.println("实际输出: " + result2);
    }
}