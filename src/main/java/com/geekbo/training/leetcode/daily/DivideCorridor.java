package com.geekbo.training.leetcode.daily;

/**
 * 2147. Number of Ways to Divide a Long Corridor
 * Hard
 * Along a long library corridor, there is a line of seats and decorative plants.
 * You are given a 0-indexed string corridor of length n consisting of letters 'S' and 'P'
 * where each 'S' represents a seat and each 'P' represents a plant.
 * <p>
 * One room divider has already been installed to the left of index 0,
 * and another to the right of index n - 1. Additional room dividers can be installed.
 * For each position between indices i - 1 and i (1 <= i <= n - 1),
 * at most one divider can be installed.
 * <p>
 * Divide the corridor into non-overlapping sections,
 * where each section has exactly two seats with any number of plants.
 * There may be multiple ways to perform the division.
 * Two ways are different if there is a position with a room divider installed in the first way but not in the second way.
 * <p>
 * Return the number of ways to divide the corridor.
 * Since the answer may be very large, return it modulo 109 + 7. If there is no way, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: corridor = "SSPPSPS"
 * Output: 3
 * Explanation: There are 3 different ways to divide the corridor.
 * The black bars in the above image indicate the two room dividers already installed.
 * Note that in each of the ways, each section has exactly two seats.
 * Example 2:
 * <p>
 * <p>
 * Input: corridor = "PPSPSP"
 * Output: 1
 * Explanation: There is only 1 way to divide the corridor, by not installing any additional dividers.
 * Installing any would create some section that does not have exactly two seats.
 * Example 3:
 * <p>
 * <p>
 * Input: corridor = "S"
 * Output: 0
 * Explanation: There is no way to divide the corridor because there will always be a section that does not have exactly two seats.
 */
public class DivideCorridor {
    /**
     * 解题思路： 首先，我们需要统计走廊中的座位数和植物数，然后判断是否满足分割走廊的条件。
     * 具体地说，座位数必须大于等于4，植物数必须大于等于1。
     * <p>
     * 接下来，我们遍历走廊中的每个可能的分割位置。对于每个位置，我们分别计算左侧和右侧的座位数。
     * 如果左侧和右侧的座位数都等于2，则该位置是一个有效的分割位置。
     * <p>
     * 最后，我们返回符合条件的分割位置的数量，取模10^9 + 7。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历走廊中的每个可能的分割位置需要O(n)的时间，其中n是走廊的长度。因此，总的时间复杂度为O(n)。
     * 空间复杂度：除了输入的走廊字符串外，我们只需要常数个额外的变量。因此，空间复杂度为O(1)。
     *
     * @param corridor
     * @return
     */
    public static int divideCorridor(String corridor) {
        // Count the number of seats and plants
        int numSeats = 0;
        int numPlants = 0;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                numSeats++;
            } else {
                numPlants++;
            }
        }

        // If there are not enough seats or plants, return 0
        if (numSeats < 4 || numPlants < 1) {
            return 0;
        }

        // Calculate the number of ways to divide the corridor
        long ways = 0;

        // Iterate through each possible divider position
        for (int i = 1; i < corridor.length(); i++) {
            int numSeatsLeft = countSeats(corridor, 0, i);
            int numSeatsRight = countSeats(corridor, i, corridor.length());

            // Check if the current position is a valid divider position
            if (numSeatsLeft == 2 && numSeatsRight == 2) {
                ways++;
            }
        }

        // Return the number of ways modulo 10^9 + 7
        return (int) (ways % (int) (1e9 + 7));
    }

    // Helper method to count the number of seats in a range of the corridor
    private static int countSeats(String corridor, int start, int end) {
        int count = 0;
        for (int i = start; i < end; i++) {
            if (corridor.charAt(i) == 'S') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        String corridor1 = "SSPPSPS";
        // Expected output: 3
        System.out.println(divideCorridor(corridor1));

        String corridor2 = "PPSPSP";
        // Expected output: 1
        System.out.println(divideCorridor(corridor2));

        String corridor3 = "S";
        // Expected output: 0
        System.out.println(divideCorridor(corridor3));
    }
}
