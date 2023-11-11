package com.geekbo.training.leetcode.daily;

/**
 * 765. Couples Holding Hands
 * Solved
 * Hard
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 * <p>
 * The people and seats are represented by an integer array row where row[i]
 * is the ID of the person sitting in the ith seat. The couples are numbered in order,
 * the first couple being (0, 1), the second couple being (2, 3),
 * and so on with the last couple being (2n - 2, 2n - 1).
 * <p>
 * Return the minimum number of swaps so that every couple is sitting side by side.
 * A swap consists of choosing any two people, then they stand up and switch seats.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * Example 2:
 * <p>
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 */
class CouplesHoldingHands {
    /**
     * 解题思路：
     * <p>
     * 首先，我们需要初始化一个长度为n的数组pos，用于存储每个人的位置。
     * 然后，我们遍历数组row，将每个人的位置信息存储到pos数组中。
     * 接下来，我们使用一个循环来遍历数组row，并检查每对情侣的位置关系。
     * 对于每对情侣，我们检查第一个人的位置和第二个人的位置是否正确。
     * 如果不正确，我们就进行交换，并更新pos数组和交换次数。
     * 最后，我们返回交换次数即可。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 初始化pos数组的时间复杂度是O(n)。
     * 遍历数组row并进行交换的时间复杂度是O(n)。
     * 因此，总的时间复杂度是O(n)。
     * 注意：这里的n表示人的数量，而不是座位的数量。
     *
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] pos = new int[n];

        // Initialize the positions array
        for (int i = 0; i < n; i++) {
            pos[row[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i += 2) {
            int x = row[i];
            int y = (x % 2 == 0) ? x + 1 : x - 1;

            if (row[i + 1] != y) {
                int idx = pos[y];
                swap(row, i + 1, idx);
                pos[row[i + 1]] = i + 1;
                pos[row[idx]] = idx;
                swaps++;
            }
        }

        return swaps;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        CouplesHoldingHands solution = new CouplesHoldingHands();

        // Test case 1
        int[] row1 = {0, 2, 1, 3};
        int expected1 = 1;
        int result1 = solution.minSwapsCouples(row1);
        System.out.println("Test case 1: " + (result1 == expected1));

        // Test case 2
        int[] row2 = {3, 2, 0, 1};
        int expected2 = 0;
        int result2 = solution.minSwapsCouples(row2);
        System.out.println("Test case 2: " + (result2 == expected2));
    }
}
