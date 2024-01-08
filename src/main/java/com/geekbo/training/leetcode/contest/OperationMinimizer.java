package com.geekbo.training.leetcode.contest;

import java.util.*;

public class OperationMinimizer {

    /**
     * 计算将x转换为y的最少操作次数
     *
     * @param x 要转换的数字x
     * @param y 目标数字y
     * @return 最少操作次数
     */
    public static int minimumOperationsToMakeEqual(int x, int y) {
        if (x <= y) {
            return y - x;
        }

        Set<Integer> visited = new HashSet<>();
        visited.add(x);
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(x);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == y) {
                    return depth;
                }

                if (visited.add(current + 1)) {
                    queue.offer(current + 1);
                }

                if (visited.add(current - 1) && current - 1 > 0) {
                    queue.offer(current - 1);
                }

                if (current % 5 == 0 && visited.add(current / 5)) {
                    queue.offer(current / 5);
                }

                if (current % 11 == 0 && visited.add(current / 11)) {
                    queue.offer(current / 11);
                }
            }
            depth++;
        }

        return -1;
    }

    public static void main(String[] args) {
        // 测试用例
        int x1 = 26, y1 = 1;
        int x2 = 54, y2 = 2;
        int x3 = 25, y3 = 30;
        System.out.println(minimumOperationsToMakeEqual(x1, y1)); // 3
        System.out.println(minimumOperationsToMakeEqual(x2, y2)); // 4
        System.out.println(minimumOperationsToMakeEqual(x3, y3)); // 5
    }

}
