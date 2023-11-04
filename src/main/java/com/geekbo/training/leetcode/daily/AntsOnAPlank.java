package com.geekbo.training.leetcode.daily;

/**
 * 1503. Last Moment Before All Ants Fall Out of a Plank
 * We have a wooden plank of the length n units. Some ants are walking on the plank,
 * each ant moves with a speed of 1 unit per second. Some of the ants move to the left,
 * the other move to the right.
 * <p>
 * When two ants moving in two different directions meet at some point,
 * they change their directions and continue moving again.
 * Assume changing directions does not take any additional time.
 * <p>
 * When an ant reaches one end of the plank at a time t, it falls out of the plank immediately.
 * <p>
 * Given an integer n and two integer arrays left and right,
 * the positions of the ants moving to the left and the right,
 * return the moment when the last ant(s) fall out of the plank.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: n = 4, left = [4,3], right = [0,1]
 * Output: 4
 * Explanation: In the image above:
 * -The ant at index 0 is named A and going to the right.
 * -The ant at index 1 is named B and going to the right.
 * -The ant at index 3 is named C and going to the left.
 * -The ant at index 4 is named D and going to the left.
 * The last moment when an ant was on the plank is t = 4 seconds.
 * After that, it falls immediately out of the plank. (i.e.,
 * We can say that at t = 4.0000000001, there are no ants on the plank).
 * Example 2:
 * <p>
 * <p>
 * Input: n = 7, left = [], right = [0,1,2,3,4,5,6,7]
 * Output: 7
 * Explanation: All ants are going to the right, the ant at index 0 needs 7 seconds to fall.
 * Example 3:
 * <p>
 * <p>
 * Input: n = 7, left = [0,1,2,3,4,5,6,7], right = []
 * Output: 7
 * Explanation: All ants are going to the left, the ant at index 7 needs 7 seconds to fall.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 104
 * 0 <= left.length <= n + 1
 * 0 <= left[i] <= n
 * 0 <= right.length <= n + 1
 * 0 <= right[i] <= n
 * 1 <= left.length + right.length <= n + 1
 * All values of left and right are unique, and each value can appear only in one of the two arrays.
 */
public class AntsOnAPlank {

    /**
     * 解题思路：
     * <p>
     * 定义一个变量maxLeft来记录所有朝左移动的蚂蚁的最大位置，初始值为0。
     * 定义一个变量maxRight来记录所有朝右移动的蚂蚁的最小位置，初始值为n。
     * 遍历数组left，更新maxLeft为所有朝左移动的蚂蚁的最大位置。
     * 遍历数组right，更新maxRight为所有朝右移动的蚂蚁的最小位置。
     * 最后，返回maxLeft和n - maxRight中的较大值，即为最后一个蚂蚁掉落的时间。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：遍历数组left和right的时间复杂度均为O(m)，其中m为left和right的长度。因此，总的时间复杂度为O(m)。
     * 空间复杂度：仅使用了常数个额外变量，因此空间复杂度为O(1)。
     *
     * @param n
     * @param left
     * @param right
     * @return
     */
    public static int getLastMoment(int n, int[] left, int[] right) {
        int maxLeft = 0;
        int maxRight = n;

        // Find the maximum position of the ants moving to the left
        for (int i = 0; i < left.length; i++) {
            maxLeft = Math.max(maxLeft, left[i]);
        }

        // Find the minimum position of the ants moving to the right
        for (int i = 0; i < right.length; i++) {
            maxRight = Math.min(maxRight, right[i]);
        }

        // Calculate the time when the last ant(s) fall out of the plank
        return Math.max(maxLeft, n - maxRight);
    }

    public static void main(String[] args) {
        // Test case 1
        int n1 = 4;
        int[] left1 = {4, 3};
        int[] right1 = {0, 1};
        int expected1 = 4;
        int result1 = getLastMoment(n1, left1, right1);
        System.out.println("Test case 1:");
        System.out.println("Expected: " + expected1);
        System.out.println("Result: " + result1);
        System.out.println();

        // Test case 2
        int n2 = 7;
        int[] left2 = {};
        int[] right2 = {0, 1, 2, 3, 4, 5, 6, 7};
        int expected2 = 7;
        int result2 = getLastMoment(n2, left2, right2);
        System.out.println("Test case 2:");
        System.out.println("Expected: " + expected2);
        System.out.println("Result: " + result2);
        System.out.println();

        // Test case 3
        int n3 = 7;
        int[] left3 = {0, 1, 2, 3, 4, 5, 6, 7};
        int[] right3 = {};
        int expected3 = 7;
        int result3 = getLastMoment(n3, left3, right3);
        System.out.println("Test case 3:");
        System.out.println("Expected: " + expected3);
        System.out.println("Result: " + result3);
    }
}
