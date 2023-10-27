package com.geekbo.training.leetcode.daily;

/**
 * Given two strings s and t,
 * return true if they are equal when both are typed into empty text editors.
 * '#' means a backspace character.
 * <p>
 * Note that after backspacing an empty text, the text will continue empty.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "ab#c", t = "ad#c"
 * Output: true
 * Explanation: Both s and t become "ac".
 * Example 2:
 * <p>
 * Input: s = "ab##", t = "c#d#"
 * Output: true
 * Explanation: Both s and t become "".
 * Example 3:
 * <p>
 * Input: s = "a#c", t = "b"
 * Output: false
 * Explanation: s becomes "c" while t becomes "b".
 */
public class BackspaceStringCompare {
    /**
     * 解题思路： 这道题可以使用双指针来解决。
     * 我们从后往前遍历字符串s和t，使用两个指针i和j分别指向字符串s和t的最后一个字符。
     * 然后，我们用两个变量skipS和skipT来记录跳过的backspace字符的个数。
     * <p>
     * 在遍历过程中，如果当前字符是backspace字符（即#），我们将相应的skip变量加1。
     * 如果当前字符不是backspace字符，而且skip变量大于0，说明需要跳过当前字符，我们将相应的skip变量减1。
     * 如果当前字符不是backspace字符，而且skip变量等于0，说明当前字符是有效字符，我们比较两个字符串的当前字符是否相等，
     * 如果不相等，则返回false。
     * <p>
     * 最后，我们还要检查是否遍历完了字符串s和t，如果只有一个字符串遍历完了，而另一个字符串还没有遍历完，则返回false。
     * <p>
     * 算法复杂度分析：
     * <p>
     * 时间复杂度：O(n+m)，其中n和m分别是字符串s和t的长度。最坏情况下，我们需要遍历整个字符串s和t。
     * 空间复杂度：O(1)，我们只需要常数级别的额外空间。
     *
     * @param s
     * @param t
     * @return
     */
    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1;
        int j = t.length() - 1;

        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                    i--;
                } else if (skipS > 0) {
                    skipS--;
                    i--;
                } else {
                    break;
                }
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                    j--;
                } else if (skipT > 0) {
                    skipT--;
                    j--;
                } else {
                    break;
                }
            }

            if (i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }

            if ((i >= 0) != (j >= 0)) {
                return false;
            }

            i--;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        BackspaceStringCompare backspaceStringCompare = new BackspaceStringCompare();

        // Test Case 1
        String s1 = "ab#c";
        String t1 = "ad#c";
        boolean result1 = backspaceStringCompare.backspaceCompare(s1, t1);
        System.out.println(result1);  // Expected output: true

        // Test Case 2
        String s2 = "ab##";
        String t2 = "c#d#";
        boolean result2 = backspaceStringCompare.backspaceCompare(s2, t2);
        System.out.println(result2);  // Expected output: true

        // Test Case 3
        String s3 = "a#c";
        String t3 = "b";
        boolean result3 = backspaceStringCompare.backspaceCompare(s3, t3);
        System.out.println(result3);  // Expected output: false
    }
}
