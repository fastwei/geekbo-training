package com.geekbo.training.leetcode.daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.
 * <p>
 * If node i has no left child then leftChild[i] will equal -1, similarly for the right child.
 * <p>
 * Note that the nodes have no values and that we only use the node numbers in this problem.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,-1,-1,-1]
 * Output: true
 * Example 2:
 * <p>
 * <p>
 * Input: n = 4, leftChild = [1,-1,3,-1], rightChild = [2,3,-1,-1]
 * Output: false
 * Example 3:
 * <p>
 * <p>
 * Input: n = 2, leftChild = [1,0], rightChild = [-1,-1]
 * Output: false
 */
public class ValidBinaryTree {
    /**
     * 判断给定的节点是否能够形成一个有效的二叉树
     *
     * @param n          节点个数
     * @param leftChild  左子节点数组
     * @param rightChild 右子节点数组
     * @return 是否能够形成一个有效的二叉树
     */
    public static boolean isValidBinaryTree(int n, int[] leftChild, int[] rightChild) {
        // 保存节点的入度，即每个节点的父节点个数
        int[] inDegrees = new int[n];
        // 统计入度为0的节点个数
        int count = 0;
        // 遍历左子节点数组和右子节点数组，更新入度数组
        for (int i = 0; i < n; i++) {
            int left = leftChild[i];
            int right = rightChild[i];
            if (left != -1) {
                inDegrees[left]++;
                count++;
                if (inDegrees[left] > 1) {
                    return false; // 左子节点的入度不能大于1
                }
            }
            if (right != -1) {
                inDegrees[right]++;
                count++;
                if (inDegrees[right] > 1) {
                    return false; // 右子节点的入度不能大于1
                }
            }
        }
        // 只有一个节点的入度为0，即根节点
        if (count != n - 1) {
            return false;
        }

        // 初始化一个队列，并将根节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
                break;
            }
        }

        // 对队列中的节点进行拓扑排序
        while (!queue.isEmpty()) {
            int node = queue.poll();
            // 访问节点的左子节点和右子节点
            int left = leftChild[node];
            int right = rightChild[node];
            if (left != -1) {
                inDegrees[left]--;
                if (inDegrees[left] == 0) {
                    queue.offer(left);
                }
            }
            if (right != -1) {
                inDegrees[right]--;
                if (inDegrees[right] == 0) {
                    queue.offer(right);
                }
            }
        }

        // 判断是否所有节点都被访问到
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 测试用例1
        int n1 = 4;
        int[] leftChild1 = {1, -1, 3, -1};
        int[] rightChild1 = {2, -1, -1, -1};
        boolean expected1 = true;
        System.out.println("Input: n = " + n1 + ", leftChild = " + Arrays.toString(leftChild1) + ", rightChild = " + Arrays.toString(rightChild1));
        System.out.println("Expected output: " + expected1);
        System.out.println("Actual output: " + isValidBinaryTree(n1, leftChild1, rightChild1));
        System.out.println();

        // 测试用例2
        int n2 = 4;
        int[] leftChild2 = {1, -1, 3, -1};
        int[] rightChild2 = {2, 3, -1, -1};
        boolean expected2 = false;
        System.out.println("Input: n = " + n2 + ", leftChild = " + Arrays.toString(leftChild2) + ", rightChild = " + Arrays.toString(rightChild2));
        System.out.println("Expected output: " + expected2);
        System.out.println("Actual output: " + isValidBinaryTree(n2, leftChild2, rightChild2));
        System.out.println();

        // 测试用例3
        int n3 = 2;
        int[] leftChild3 = {1, 0};
        int[] rightChild3 = {-1, -1};
        boolean expected3 = false;
        System.out.println("Input: n = " + n3 + ", leftChild = " + Arrays.toString(leftChild3) + ", rightChild = " + Arrays.toString(rightChild3));
        System.out.println("Expected output: " + expected3);
        System.out.println("Actual output: " + isValidBinaryTree(n3, leftChild3, rightChild3));
    }
}
