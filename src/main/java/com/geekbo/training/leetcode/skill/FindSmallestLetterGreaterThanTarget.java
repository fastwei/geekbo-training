package com.geekbo.training.leetcode.skill;

/**
 * You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.
 * <p>
 * Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: letters = ["c","f","j"], target = "a"
 * Output: "c"
 * Explanation: The smallest character that is lexicographically greater than 'a' in letters is 'c'.
 * Example 2:
 * <p>
 * Input: letters = ["c","f","j"], target = "c"
 * Output: "f"
 * Explanation: The smallest character that is lexicographically greater than 'c' in letters is 'f'.
 * Example 3:
 * <p>
 * Input: letters = ["x","x","y","y"], target = "z"
 * Output: "x"
 * Explanation: There are no characters in letters that is lexicographically greater
 * than 'z' so we return letters[0].
 */
public class FindSmallestLetterGreaterThanTarget {
    /**
     * 返回在有序数组中大于目标字符的最小字符
     * 如果不存在这样的字符，则返回数组的第一个字符
     * 解题思路：
     * <p>
     * 给定的字符数组是有序的，我们可以使用二分查找来找到大于目标字符的最小字符。
     * 使用两个指针left和right分别指向数组的开头和结尾。
     * 在每一次迭代中，我们计算中间索引mid，并比较letters[mid]与目标字符的大小关系。
     * 如果letters[mid]小于等于目标字符，说明目标字符在右侧，我们将left指针移到mid+1的位置。
     * 如果letters[mid]大于目标字符，说明目标字符在左侧，我们将right指针移到mid-1的位置。
     * 重复上述步骤直到left指针超过right指针，此时letters[left]就是大于目标字符的最小字符。
     * 如果left小于数组的长度，则返回letters[left]，否则返回数组的第一个字符。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(log n)，其中n是字符数组的长度。二分查找的时间复杂度是对数级别的。
     * 空间复杂度：O(1)，只使用了常数级别的额外空间。
     *
     * @param letters 有序字符数组
     * @param target  目标字符
     * @return 大于目标字符的最小字符
     */
    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (letters[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left < n ? letters[left] : letters[0];
    }

    public static void main(String[] args) {
        char[] letters1 = {'c', 'f', 'j'};
        char target1 = 'a';
        System.out.println(nextGreatestLetter(letters1, target1)); // Expected: 'c'

        char[] letters2 = {'c', 'f', 'j'};
        char target2 = 'c';
        System.out.println(nextGreatestLetter(letters2, target2)); // Expected: 'f'

        char[] letters3 = {'x', 'x', 'y', 'y'};
        char target3 = 'z';
        System.out.println(nextGreatestLetter(letters3, target3)); // Expected: 'x'
    }
}
