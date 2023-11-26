package com.geekbo.training.leetcode.daily;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 388. Longest Absolute File Path
 * Medium
 * Suppose we have a file system that stores both files and directories. An example of one system is represented in the following picture:
 * <p>
 * <p>
 * <p>
 * Here, we have dir as the only directory in the root. dir contains two subdirectories, subdir1 and subdir2. subdir1 contains a file file1.ext and subdirectory subsubdir1. subdir2 contains a subdirectory subsubdir2, which contains a file file2.ext.
 * <p>
 * In text form, it looks like this (with ⟶ representing the tab character):
 * <p>
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 * If we were to write this representation in code, it will look like this: "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext". Note that the '\n' and '\t' are the new-line and tab characters.
 * <p>
 * Every file and directory has a unique absolute path in the file system, which is the order of directories that must be opened to reach the file/directory itself, all concatenated by '/'s. Using the above example, the absolute path to file2.ext is "dir/subdir2/subsubdir2/file2.ext". Each directory name consists of letters, digits, and/or spaces. Each file name is of the form name.extension, where name and extension consist of letters, digits, and/or spaces.
 * <p>
 * Given a string input representing the file system in the explained format, return the length of the longest absolute path to a file in the abstracted file system. If there is no file in the system, return 0.
 * <p>
 * Note that the testcases are generated such that the file system is valid and no file or directory name has length 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 * Output: 20
 * Explanation: We have only one file, and the absolute path is "dir/subdir2/file.ext" of length 20.
 * Example 2:
 * <p>
 * <p>
 * Input: input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * Output: 32
 * Explanation: We have two files:
 * "dir/subdir1/file1.ext" of length 21
 * "dir/subdir2/subsubdir2/file2.ext" of length 32.
 * We return 32 since it is the longest absolute path to a file.
 * Example 3:
 * <p>
 * Input: input = "a"
 * Output: 0
 * Explanation: We do not have any files, just a single directory named "a".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= input.length <= 104
 * input may contain lowercase or uppercase English letters, a new line character '\n', a tab character '\t', a dot '.', a space ' ', and digits.
 * All file and directory names have positive length.
 */
public class LongestAbsoluteFilePath {
    /**
     * 解题思路：
     * <p>
     * 首先，我们将输入的字符串按换行符分割成每一行的路径。
     * 然后，我们使用一个栈来存储每一级路径的长度。
     * 对于每一行路径，我们找到它的缩进级别，然后将栈中大于该级别的路径长度出栈。
     * 计算当前路径的长度，并将其入栈。
     * 如果当前路径包含文件名（即包含点号），我们更新最长路径的长度。
     * 最后，返回最长路径的长度。
     * <p>
     * 算法复杂度：
     * <p>
     * 时间复杂度：我们需要遍历输入的每一行路径，所以时间复杂度为O(n)，其中n是输入字符串的长度。
     * 空间复杂度：我们使用了一个栈来存储路径长度，所以空间复杂度为O(n)。
     * <p>
     * 总结：
     * <p>
     * 遍历输入的每一行路径，使用栈来存储路径长度，并更新最长路径的长度。
     * 时间复杂度为O(n)，空间复杂度为O(n)。
     *
     * @param input
     * @return
     */
    public int lengthLongestPath(String input) {
        String[] paths = input.split("\n");
        Deque<Integer> stack = new ArrayDeque<>();
        int maxLength = 0;

        for (String path : paths) {
            int level = path.lastIndexOf('\t') + 1;
            while (stack.size() > level) {
                stack.pop();
            }

            int len = (stack.isEmpty() ? 0 : stack.peek()) + path.length() - level + 1;
            stack.push(len);

            if (path.contains(".")) {
                maxLength = Math.max(maxLength, len - 1);
            }
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestAbsoluteFilePath solution = new LongestAbsoluteFilePath();

        // 测试用例1
        String input1 = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        int result1 = solution.lengthLongestPath(input1);
        System.out.println("测试用例1:");
        System.out.println(result1); // 预期输出: 20

        // 测试用例2
        String input2 = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        int result2 = solution.lengthLongestPath(input2);
        System.out.println("测试用例2:");
        System.out.println(result2); // 预期输出: 32

        // 测试用例3
        String input3 = "a";
        int result3 = solution.lengthLongestPath(input3);
        System.out.println("测试用例3:");
        System.out.println(result3); // 预期输出: 0
    }
}

