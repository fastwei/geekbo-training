package com.geekbo.training.leetcode.greedy;

import java.util.Arrays;

/**
 * 455. Assign Cookies
 * Easy
 * Assume you are an awesome parent and want to give your children some cookies.
 * But, you should give each child at most one cookie.
 * <p>
 * Each child i has a greed factor g[i], which is the minimum size of a cookie that the child will be content with; and each cookie j has a size s[j]. If s[j] >= g[i], we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the number of your content children and output the maximum number.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: g = [1,2,3], s = [1,1]
 * Output: 1
 * Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor is 1 content.
 * You need to output 1.
 * Example 2:
 * <p>
 * Input: g = [1,2], s = [1,2,3]
 * Output: 2
 * Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * You have 3 cookies and their sizes are big enough to gratify all of the children,
 * You need to output 2.
 */
public class AssignCookies {
    /**
     * 解题思路：
     * <p>
     * 将贪心因子数组 g 和饼干数组 s 排序，从小到大。
     * 使用两个指针 i 和 j 分别指向贪心因子数组和饼干数组的起始位置。
     * 遍历贪心因子数组和饼干数组，如果当前饼干大小能够满足当前贪心因子，则将饼干分配给该贪心因子，并将两个指针分别向后移动一位；否则，只移动饼干指针。
     * 最终，返回满足的贪心因子个数。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：排序贪心因子数组和饼干数组的时间复杂度为 O(nlogn)，其中 n 是数组的长度。遍历贪心因子数组和饼干数组的时间复杂度为 O(n)。因此，总时间复杂度为 O(nlogn)。
     * 空间复杂度：排序所需的额外空间为 O(logn)，遍历过程中使用的额外空间为 O(1)。因此，总空间复杂度为 O(logn)。
     *
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        // Sort the greed factors and cookie sizes in ascending order
        Arrays.sort(g);
        Arrays.sort(s);

        int contentChildren = 0;
        int i = 0; // index for greed factors
        int j = 0; // index for cookie sizes

        // Iterate through the greed factors and cookie sizes
        while (i < g.length && j < s.length) {
            // If the current cookie size can satisfy the current greed factor,
            // assign the cookie to the child and move on to the next child and cookie
            if (s[j] >= g[i]) {
                contentChildren++;
                i++;
                j++;
            }
            // If the current cookie size cannot satisfy the current greed factor,
            // move on to the next cookie
            else {
                j++;
            }
        }

        return contentChildren;
    }

    public static void main(String[] args) {
        // Test cases
        int[] g1 = {1, 2, 3};
        int[] s1 = {1, 1};
        System.out.println("Expected Output: 1");
        System.out.println("Actual Output: " + findContentChildren(g1, s1) + "\n");

        int[] g2 = {1, 2};
        int[] s2 = {1, 2, 3};
        System.out.println("Expected Output: 2");
        System.out.println("Actual Output: " + findContentChildren(g2, s2));
    }
}
