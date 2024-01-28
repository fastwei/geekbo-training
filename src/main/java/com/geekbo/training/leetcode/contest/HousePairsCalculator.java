package com.geekbo.training.leetcode.contest;
//381.2
public class HousePairsCalculator {

    public int[] countOfPairs(int n, int x, int y) {
        int[] result = new int[n];
        // 遍历所有可能的房屋对
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // 计算最短路径长度
                int dist = Math.min(j - i, Math.abs(x - i) + Math.abs(y - j) + 1);
                dist = Math.min(dist, Math.abs(y - i) + Math.abs(x - j) + 1);
                // 更新结果数组，下标需要减1，因为数组是从0开始的
                result[dist - 1]++;
            }
        }

        // 由于每对房屋可以双向计数，所以结果需要乘以2
        for (int k = 0; k < n; k++) {
            result[k] *= 2;
        }

        return result;
    }

    // 主方法，用于测试。
    public static void main(String[] args) {
        HousePairsCalculator calculator = new HousePairsCalculator();

        // 测试用例1
        int[] result1 = calculator.countOfPairs(3, 1, 3);
        System.out.println("Expected: [6, 0, 0], Actual: " + java.util.Arrays.toString(result1));

        // 测试用例2
        int[] result2 = calculator.countOfPairs(5, 2, 4);
        System.out.println("Expected: [10, 8, 2, 0, 0], Actual: " + java.util.Arrays.toString(result2));

        // 测试用例3
        int[] result3 = calculator.countOfPairs(4, 1, 1);
        System.out.println("Expected: [6, 4, 2, 0], Actual: " + java.util.Arrays.toString(result3));
    }
}
