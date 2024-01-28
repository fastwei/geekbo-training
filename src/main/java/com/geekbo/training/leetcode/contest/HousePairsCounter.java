package com.geekbo.training.leetcode.contest;
//381.12
public class HousePairsCounter {

    public int[] countOfPairs(int n, int x, int y) {
        int[] result = new int[n];
        // Normalize x and y to make x the smaller
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }

        // Calculate pairs for each distance k
        for (int k = 1; k < n; k++) {
            for (int start = 1; start <= n; start++) {
                int end = start + k;
                if (end <= n) {
                    result[k - 1] += 2; // Count both directions
                }

                // Count pairs using the shortcut street
                if (start < x && end > y && end <= n) {
                    if (k >= (x - start) + 1 + (end - y)) {
                        result[(x - start) + (end - y)] += 2; // Count both directions
                    }
                }
            }
        }

        // Correct the double counting for pairs where x and y are endpoints
        for (int k = y - x; k < n; k++) {
            result[k] -= 2;
        }

        return result;
    }

    // Test the method
    public static void main(String[] args) {
        HousePairsCounter counter = new HousePairsCounter();

        // Test Case 1
        int n1 = 3, x1 = 1, y1 = 3;
        int[] result1 = counter.countOfPairs(n1, x1, y1);
        System.out.println("Test Case 1: " + java.util.Arrays.toString(result1));
        //预期结果：6,0,0

        // Test Case 2
        int n2 = 5, x2 = 2, y2 = 4;
        int[] result2 = counter.countOfPairs(n2, x2, y2);
        System.out.println("Test Case 2: " + java.util.Arrays.toString(result2));
        //预期结果：10,8,2,0,0

        // Test Case 3
        int n3 = 4, x3 = 1, y3 = 1;
        int[] result3 = counter.countOfPairs(n3, x3, y3);
        System.out.println("Test Case 3: " + java.util.Arrays.toString(result3));
        //预期结果：6,4,2,0
    }
}
